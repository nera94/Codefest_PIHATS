package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

public class MainController implements Initializable {
	
	@FXML
    private LineChart<?, ?> linechart;
	
	@FXML
    private BarChart<?, ?> barchart;
	
	@FXML
    private BarChart<?, ?> barchat2;
	
	@FXML
    private PieChart pie_chart;
	
	@FXML
    private JFXButton btndash;

    @FXML
    private JFXButton btnitem;
    
    @FXML
    private JFXButton btnswitch;
    
    @FXML
    private AnchorPane itempane;
    
    @FXML
    private AnchorPane dashpane;
    
    @FXML
    private AnchorPane switchpane;
    
    @FXML
    private JFXTreeTableView<Model> itemview;
    
    @FXML
    private TreeTableColumn<Model, String> itemcol;

    @FXML
    private TreeTableColumn<Model, String> feature1col;

    @FXML
    private TreeTableColumn<Model, String> feature2col;

    @FXML
    private TreeTableColumn<Model, String> feature3col;

    @FXML
    private TreeTableColumn<Model, String> feature4col;

    @FXML
    private TreeTableColumn<Model, String> feature5col;
    
    @FXML
	public void chartmove() {
		barchart.setOpacity(1.0);
	}
	
    ObservableList<Model> listitem=FXCollections.observableArrayList();
    ObservableList<Integer> countlist=FXCollections.observableArrayList();
    private static FirebaseDatabase firebaseDatabase;
  
    boolean isAlive = true; //change it  to falst whenever u want to stop the thread
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Linechart();
		//Barchart();
		//Barchart2();
		//Piechart();
		//switchpane.setVisible(false);
		
		//StackBarchart();
		try {
			initFirebase();
			
			query();
//			query1();
//			query2();
//			query3();
//			query4();
			table();
		//	sensorss();
			//run();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void Linechart(String sensor,int level) {
			
		XYChart.Series Senasor4=new XYChart.Series();

		

		if(sensor.equalsIgnoreCase("SE004")){
			
			Senasor4.getData().addAll(new XYChart.Data<>(sensor,level));
		}

		
		linechart.getData().addAll(Senasor4);

		
	}
	
public void Barchart() {
		
		XYChart.Series series1=new XYChart.Series();
		series1.getData().addAll(new XYChart.Data("Milk",30));
	
		
		XYChart.Series series2=new XYChart.Series();
		series1.getData().addAll(new XYChart.Data("apple",10));
	
		
		XYChart.Series series3=new XYChart.Series();
		series1.getData().addAll(new XYChart.Data("egg",20));
	
		barchart.getData().addAll(series1,series2,series3);
		
	}

public void Barchart2(String sensor,int item) {
	
	
	XYChart.Series Senasor1=new XYChart.Series();
	XYChart.Series Senasor2=new XYChart.Series();
	XYChart.Series Senasor3=new XYChart.Series();
	XYChart.Series Senasor4=new XYChart.Series();
	XYChart.Series Senasor5=new XYChart.Series();

	
	if(sensor.equalsIgnoreCase("SE004")){
		System.out.println("Which IOT device 1:"+sensor);
		Senasor4.getData().addAll(new XYChart.Data<>(sensor,item));
	}

	barchat2.getData().addAll(Senasor4);
	
}
public void Piechart(String sensor,int level) {
	
	ObservableList<PieChart.Data> list4= FXCollections.observableArrayList();

	if(sensor.equalsIgnoreCase("SE004")){
		System.out.println("yohan nerangas enanayake");
		list4.addAll(new PieChart.Data(sensor, level));
	}

	pie_chart.setData(list4);

	
}
public void dash() {
	btndash.setOnAction(event->{
		dashpane.setVisible(true);
		dashpane.toFront();
		//switchpane.setVisible(true);
	});
}
public void item() {
	
	btnitem.setOnAction(event->{
		itempane.setVisible(true);
		itempane.toFront();
		//switchpane.setVisible(false);
	});
}

//public void btnswitch() {
//	
//	btnswitch.setOnAction(event->{
//		switchpane.setVisible(true);
//		switchpane.toFront();
//	});
//}


public void table() {
	
	//set the value for employee colem
	this.itemcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model,String>, ObservableValue<String>>() {
		
		//this will take a value from model class using celldatafeatures
		@Override
		public ObservableValue<String> call(CellDataFeatures<Model, String> param) {
			// TODO Auto-generated method stub
			return param.getValue().getValue().itemname;
			
		}
	});
	this.feature1col.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model,String>, ObservableValue<String>>() {
		
		//this will take a value from model class using celldatafeatures
		@Override
		public ObservableValue<String> call(CellDataFeatures<Model, String> param) {
			// TODO Auto-generated method stub
			return param.getValue().getValue().feature1;
			
		}
	});
	this.feature2col.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model,String>, ObservableValue<String>>() {
		
		//this will take a value from model class using celldatafeatures
		@Override
		public ObservableValue<String> call(CellDataFeatures<Model, String> param) {
			// TODO Auto-generated method stub
			return param.getValue().getValue().feature2;
			
		}
	});
	this.feature3col.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model,String>, ObservableValue<String>>() {
		
		//this will take a value from model class using celldatafeatures
		@Override
		public ObservableValue<String> call(CellDataFeatures<Model, String> param) {
			// TODO Auto-generated method stub
			return param.getValue().getValue().feature3;
			
		}
	});
	this.feature4col.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model,String>, ObservableValue<String>>() {
		
		//this will take a value from model class using celldatafeatures
		@Override
		public ObservableValue<String> call(CellDataFeatures<Model, String> param) {
			// TODO Auto-generated method stub
			return param.getValue().getValue().feature4;
			
		}
	});
	this.feature5col.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model,String>, ObservableValue<String>>() {
		
		//this will take a value from model class using celldatafeatures
		@Override
		public ObservableValue<String> call(CellDataFeatures<Model, String> param) {
			// TODO Auto-generated method stub
			return param.getValue().getValue().feature5;
			
		}
	});

		TreeItem<Model> root=new RecursiveTreeItem<Model>(listitem , RecursiveTreeObject :: getChildren);
		itemview.setRoot(root);
		itemview.setShowRoot(false);
		

		
		

}
public void initFirebase() throws IOException {
	try {
        System.out.println("Neranga");
        FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
                .setDatabaseUrl("https://hackathon2018-a23ed.firebaseio.com/")
                .setCredential((FirebaseCredentials.fromCertificate(new FileInputStream(new File("C:\\Users\\YNS(Nera)\\Desktop\\codefest\\private.json")))))
                .build();
        FirebaseApp.initializeApp(firebaseOptions);
        firebaseDatabase = FirebaseDatabase.getInstance();
        System.out.println("yohan");
    } catch (FileNotFoundException ex) {
        ex.printStackTrace();
    }

}
public void query()  {
	System.out.println("arshad");
	
	 DatabaseReference databaseReference = firebaseDatabase.getReference("calcium/");
	 final CountDownLatch latch = new CountDownLatch(1);
	 
	 System.out.println("si");
	 
	 databaseReference.addChildEventListener(new ChildEventListener() {
			
			public void onChildRemoved(DataSnapshot arg0) {
				// TODO Auto-generated method stub
				
				latch.countDown();
				
			}
			
			public void onChildMoved(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub
				latch.countDown();
				
			}
			
			public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
//					
//				String sensorID=dataSnapshot.child("sid").getValue(String.class).toString();
//				String ItemID=dataSnapshot.child("iid").getValue(String.class).toString();
//				list.addAll(new Model(sensorID, ItemID, "HD", "USB Connection", "LED", "24-channels"));
//				System.out.println(sensorID);
//				System.out.println(ItemID);
//				System.out.println("The new post title is: " +dataSnapshot.getKey());
//				latch.countDown();
//				
		}
			
			public void onChildAdded(DataSnapshot dataSnapshot, String arg1) {
				
				String sensorID=dataSnapshot.child("sid").getValue(String.class).toString();
				String ItemID=dataSnapshot.child("iid").getValue(String.class).toString();
				int callevel=dataSnapshot.child("calciumlevel").getValue(int.class);
				listitem.addAll(new Model(sensorID, ItemID, "HD", "USB Connection", "LED", "24-channels"));
				 Barchart2(sensorID,callevel);
				 Piechart(sensorID,callevel);
				 Linechart(sensorID,callevel);
				System.out.println(sensorID);
				System.out.println("The new post title is: " +dataSnapshot.getKey());
				latch.countDown();
				System.out.println("The new post count: " +dataSnapshot.getChildrenCount());
		
				latch.countDown();
				table();
			}
			
			
			
			
			public void onCancelled(DatabaseError arg0) {
				
				latch.countDown();
				
				
			}
		});

		 try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	
}
//public void query1()  {
//	System.out.println("arshad");
//	
//	 DatabaseReference databaseReference = firebaseDatabase.getReference("fat/");
//	 final CountDownLatch latch = new CountDownLatch(1);
//	 
//	 System.out.println("si");
//	 
//	 databaseReference.addChildEventListener(new ChildEventListener() {
//			
//			public void onChildRemoved(DataSnapshot arg0) {
//				// TODO Auto-generated method stub
//				
//				latch.countDown();
//				
//			}
//			
//			public void onChildMoved(DataSnapshot arg0, String arg1) {
//				// TODO Auto-generated method stub
//				latch.countDown();
//				
//			}
//			
//			public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
////					
////				String sensorID=dataSnapshot.child("sid").getValue(String.class).toString();
////				String ItemID=dataSnapshot.child("iid").getValue(String.class).toString();
////				list.addAll(new Model(sensorID, ItemID, "HD", "USB Connection", "LED", "24-channels"));
////				System.out.println(sensorID);
////				System.out.println(ItemID);
////				System.out.println("The new post title is: " +dataSnapshot.getKey());
////				latch.countDown();
////				
//		}
//			
//			public void onChildAdded(DataSnapshot dataSnapshot, String arg1) {
//				
//				String sensorID=dataSnapshot.child("sid").getValue(String.class).toString();
//				String ItemID=dataSnapshot.child("iid").getValue(String.class).toString();
//				int callevel=dataSnapshot.child("calciumlevel").getValue(int.class);
//				listitem.addAll(new Model(sensorID, ItemID, "HD", "USB Connection", "LED", "24-channels"));
//				Barchart2(sensorID,callevel);
//				Piechart(sensorID,callevel);
//				Linechart(sensorID,callevel);
//				System.out.println(sensorID);
//				System.out.println("The new post title is: " +dataSnapshot.getKey());
//				latch.countDown();
//				System.out.println("The new post count: " +dataSnapshot.getChildrenCount());
//		
//				latch.countDown();
//				//table();
//			}
//			
//			
//			
//			
//			public void onCancelled(DatabaseError arg0) {
//				
//				latch.countDown();
//				
//				
//			}
//		});
//	 System.out.println("sadgasygdyasgdyusagdyugsayugdugd :"+countlist);
//	     y= countlist.sorted().lastIndexOf(countlist.size()-1);
//		 System.out.println("yoga");
//		 System.out.println("final value of count :" + y);
//		 try {
//			latch.await();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			System.out.println(e);
//		}
//	
//}
//public void query2()  {
//	System.out.println("arshad");
//	
//	 DatabaseReference databaseReference = firebaseDatabase.getReference("iron/");
//	 final CountDownLatch latch = new CountDownLatch(1);
//	 
//	 System.out.println("si");
//	 
//	 databaseReference.addChildEventListener(new ChildEventListener() {
//			
//			public void onChildRemoved(DataSnapshot arg0) {
//				// TODO Auto-generated method stub
//				
//				latch.countDown();
//				
//			}
//			
//			public void onChildMoved(DataSnapshot arg0, String arg1) {
//				// TODO Auto-generated method stub
//				latch.countDown();
//				
//			}
//			
//			public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
////					
////				String sensorID=dataSnapshot.child("sid").getValue(String.class).toString();
////				String ItemID=dataSnapshot.child("iid").getValue(String.class).toString();
////				list.addAll(new Model(sensorID, ItemID, "HD", "USB Connection", "LED", "24-channels"));
////				System.out.println(sensorID);
////				System.out.println(ItemID);
////				System.out.println("The new post title is: " +dataSnapshot.getKey());
////				latch.countDown();
////				
//		}
//			
//			public void onChildAdded(DataSnapshot dataSnapshot, String arg1) {
//				
//				String sensorID=dataSnapshot.child("sid").getValue(String.class).toString();
//				String ItemID=dataSnapshot.child("iid").getValue(String.class).toString();
//				int callevel=dataSnapshot.child("calciumlevel").getValue(int.class);
//				listitem.addAll(new Model(sensorID, ItemID, "HD", "USB Connection", "LED", "24-channels"));
//				Barchart2(sensorID,callevel);
//				Piechart(sensorID,callevel);
//				Linechart(sensorID,callevel);
//				System.out.println(sensorID);
//				System.out.println("The new post title is: " +dataSnapshot.getKey());
//				latch.countDown();
//				System.out.println("The new post count: " +dataSnapshot.getChildrenCount());
//		
//				latch.countDown();
//				//table();
//			}
//			
//			
//			
//			
//			public void onCancelled(DatabaseError arg0) {
//				
//				latch.countDown();
//				
//				
//			}
//		});
//	 System.out.println("sadgasygdyasgdyusagdyugsayugdugd :"+countlist);
//	     y= countlist.sorted().lastIndexOf(countlist.size()-1);
//		 System.out.println("yoga");
//		 System.out.println("final value of count :" + y);
//		 try {
//			latch.await();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			System.out.println(e);
//		}
//	
//}
//public void query3()  {
//	System.out.println("arshad");
//	
//	 DatabaseReference databaseReference = firebaseDatabase.getReference("vitam/");
//	 final CountDownLatch latch = new CountDownLatch(1);
//	 
//	 System.out.println("si");
//	 
//	 databaseReference.addChildEventListener(new ChildEventListener() {
//			
//			public void onChildRemoved(DataSnapshot arg0) {
//				// TODO Auto-generated method stub
//				
//				latch.countDown();
//				
//			}
//			
//			public void onChildMoved(DataSnapshot arg0, String arg1) {
//				// TODO Auto-generated method stub
//				latch.countDown();
//				
//			}
//			
//			public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
////					
////				String sensorID=dataSnapshot.child("sid").getValue(String.class).toString();
////				String ItemID=dataSnapshot.child("iid").getValue(String.class).toString();
////				list.addAll(new Model(sensorID, ItemID, "HD", "USB Connection", "LED", "24-channels"));
////				System.out.println(sensorID);
////				System.out.println(ItemID);
////				System.out.println("The new post title is: " +dataSnapshot.getKey());
////				latch.countDown();
////				
//		}
//			
//			public void onChildAdded(DataSnapshot dataSnapshot, String arg1) {
//				
//				String sensorID=dataSnapshot.child("sid").getValue(String.class).toString();
//				String ItemID=dataSnapshot.child("iid").getValue(String.class).toString();
//				int callevel=dataSnapshot.child("calciumlevel").getValue(int.class);
//				listitem.addAll(new Model(sensorID, ItemID, "HD", "USB Connection", "LED", "24-channels"));
//				Barchart2(sensorID,callevel);
//				Piechart(sensorID,callevel);
//				Linechart(sensorID,callevel);
//				System.out.println(sensorID);
//				System.out.println("The new post title is: " +dataSnapshot.getKey());
//				latch.countDown();
//				System.out.println("The new post count: " +dataSnapshot.getChildrenCount());
//		
//				latch.countDown();
//				//table();
//			}
//			
//			
//			
//			
//			public void onCancelled(DatabaseError arg0) {
//				
//				latch.countDown();
//				
//				
//			}
//		});
//	 System.out.println("sadgasygdyasgdyusagdyugsayugdugd :"+countlist);
//	     y= countlist.sorted().lastIndexOf(countlist.size()-1);
//		 System.out.println("yoga");
//		 System.out.println("final value of count :" + y);
//		 try {
//			latch.await();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			System.out.println(e);
//		}
//	
//}
//public void query4()  {
//	System.out.println("arshad");
//	
//	 DatabaseReference databaseReference = firebaseDatabase.getReference("weight/");
//	 final CountDownLatch latch = new CountDownLatch(1);
//	 
//	 System.out.println("si");
//	 
//	 databaseReference.addChildEventListener(new ChildEventListener() {
//			
//			public void onChildRemoved(DataSnapshot arg0) {
//				// TODO Auto-generated method stub
//				
//				latch.countDown();
//				
//			}
//			
//			public void onChildMoved(DataSnapshot arg0, String arg1) {
//				// TODO Auto-generated method stub
//				latch.countDown();
//				
//			}
//			
//			public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
////					
////				String sensorID=dataSnapshot.child("sid").getValue(String.class).toString();
////				String ItemID=dataSnapshot.child("iid").getValue(String.class).toString();
////				list.addAll(new Model(sensorID, ItemID, "HD", "USB Connection", "LED", "24-channels"));
////				System.out.println(sensorID);
////				System.out.println(ItemID);
////				System.out.println("The new post title is: " +dataSnapshot.getKey());
////				latch.countDown();
////				
//		}
//			
//			public void onChildAdded(DataSnapshot dataSnapshot, String arg1) {
//				
//				String sensorID=dataSnapshot.child("sid").getValue(String.class).toString();
//				String ItemID=dataSnapshot.child("iid").getValue(String.class).toString();
//				int callevel=dataSnapshot.child("calciumlevel").getValue(int.class);
//				listitem.addAll(new Model(sensorID, ItemID, "HD", "USB Connection", "LED", "24-channels"));
//				Barchart2(sensorID,callevel);
//				Piechart(sensorID,callevel);
//				Linechart(sensorID,callevel);
//				System.out.println(sensorID);
//				System.out.println("The new post title is: " +dataSnapshot.getKey());
//				latch.countDown();
//				System.out.println("The new post count: " +dataSnapshot.getChildrenCount());
//		
//				latch.countDown();
//				//table();
//			}
//			
//			
//			
//			
//			public void onCancelled(DatabaseError arg0) {
//				
//				latch.countDown();
//				
//				
//			}
//		});
//	 System.out.println("sadgasygdyasgdyusagdyugsayugdugd :"+countlist);
//	     y= countlist.sorted().lastIndexOf(countlist.size()-1);
//		 System.out.println("yoga");
//		 System.out.println("final value of count :" + y);
//		 try {
//			latch.await();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			System.out.println(e);
//		}
//	
//}



	
}
