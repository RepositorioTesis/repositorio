package utils;

import java.util.HashMap;

import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.ComboBox;

public class Controls {

	public static ComboBox crearCombo(HashMap<String, ?> mapa){
		ComboBox c = new ComboBox();
		c.setInputPrompt("Elija una opcion");
		for ( String o : mapa.keySet()){
			Object item = mapa.get(o); 
			c.addItem(item);
			c.setItemCaption(item,o.toString() );
		}
		c.setNullSelectionAllowed(false);
		c.setImmediate(true);
		c.setTextInputAllowed(false);
		return c;
	}
}
