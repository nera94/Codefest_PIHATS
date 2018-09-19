package application;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Model extends RecursiveTreeObject<Model> {
	
	StringProperty itemname,feature1,feature2,feature3,feature4,feature5;
	
	public Model(String itemname,String feature1,String feature2,String feature3,String feature4,String feature5) {
		
		this.itemname = new SimpleStringProperty(itemname);
		this.feature1 = new SimpleStringProperty(feature1);
		this.feature2 = new SimpleStringProperty(feature2);
		this.feature3 = new SimpleStringProperty(feature3);
		this.feature4 = new SimpleStringProperty(feature4);
		this.feature5 = new SimpleStringProperty(feature5);
	}

	public StringProperty getItemname() {
		return itemname;
	}

	public void setItemname(StringProperty itemname) {
		this.itemname = itemname;
	}

	public StringProperty getFeature1() {
		return feature1;
	}

	public void setFeature1(StringProperty feature1) {
		this.feature1 = feature1;
	}

	public StringProperty getFeature2() {
		return feature2;
	}

	public void setFeature2(StringProperty feature2) {
		this.feature2 = feature2;
	}

	public StringProperty getFeature3() {
		return feature3;
	}

	public void setFeature3(StringProperty feature3) {
		this.feature3 = feature3;
	}

	public StringProperty getFeature4() {
		return feature4;
	}

	public void setFeature4(StringProperty feature4) {
		this.feature4 = feature4;
	}

	public StringProperty getFeature5() {
		return feature5;
	}

	public void setFeature5(StringProperty feature5) {
		this.feature5 = feature5;
	}
	
	

}
