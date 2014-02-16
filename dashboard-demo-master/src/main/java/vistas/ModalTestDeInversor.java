package vistas;

import utils.HibernateUtil;

import com.vaadin.demo.dashboard.DashboardUI;
import com.vaadin.demo.dashboard.Modal;
import com.vaadin.demo.domain.Usuario;
import com.vaadin.demo.domain.UsuarioDetalle;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class ModalTestDeInversor extends Modal{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4392770758088467832L;
	private OptionGroup pregunta1;
	private OptionGroup pregunta2;
	private OptionGroup pregunta3;
	private OptionGroup pregunta4;
	private OptionGroup pregunta5;
	private OptionGroup pregunta6;
	private OptionGroup pregunta7;

	public ModalTestDeInversor() {
		
		super("Testeamela","Cancelar" ,"Test de Inversor");
		this.setLayout(crearForm());
		this.setHeight("600px");
		this.setWidth("600px");
	}

	private Component crearForm() {
		Panel panelForm = new Panel();
		CssLayout form = new  CssLayout();
		//form.setMargin(true);
		 pregunta1 = new OptionGroup();
		pregunta1.setCaption("Planeo iniciar el retiro de fondos (efectivo) de mi cartera a dentro de:");
		pregunta1.addItem(1);
		pregunta1.setItemCaption(1, "Menos de 3 años");
		pregunta1.addItem(3);
		pregunta1.setItemCaption(3, "Entre 3 y 5 años");
		pregunta1.addItem(7);
		pregunta1.setItemCaption(7, "Entre 6 y 10 años");
		pregunta1.addItem(10);
		pregunta1.setItemCaption(10, "Dentro de 11 o más");
		
		 pregunta2 = new OptionGroup();
		pregunta2.setCaption("A partir del momento que decido empezar a retirar mis fondos, planeo retirarlos en:");
		pregunta2.addItem(0);
		pregunta2.setItemCaption(0, "Menos de 2 años");
		pregunta2.addItem(1);
		pregunta2.setItemCaption(1, "Entre 2 y 5 años");
		pregunta2.addItem(4);
		pregunta2.setItemCaption(4, "Entre 6 y 10 años");
		pregunta2.addItem(8);
		pregunta2.setItemCaption(8, "Dentro de 11 o más");
		
		 pregunta3 = new OptionGroup();
		pregunta3.setCaption("Describiría mis conocimientos sobre INVERTIR como:");
		pregunta3.addItem(0);
		pregunta3.setItemCaption(0, "Nulos");
		pregunta3.addItem(2);
		pregunta3.setItemCaption(2, "Limitados");
		pregunta3.addItem(4);
		pregunta3.setItemCaption(4, "Buenos");
		pregunta3.addItem(6);
		pregunta3.setItemCaption(6, "Muy buenos");

		 pregunta4 = new OptionGroup();
		pregunta4.setCaption("Cuando invierto mi dinero, estoy:");
		pregunta4.addItem(0);
		pregunta4.setItemCaption(0, "Mayormente preocupado por las pérdidas de valor de mi cartera");
		pregunta4.addItem(4);
		pregunta4.setItemCaption(4, "Preocupado por las pérdidas y ganancias de valor de mi cartera");
		pregunta4.addItem(8);
		pregunta4.setItemCaption(8, "Mayormente preocupado por las ganancias de valor de mi cartera");
		
		 pregunta5 = new OptionGroup();
		pregunta5.setCaption("Que inversiones realiza o ha realizado en forma más frecuente:");
		pregunta5.addItem(0);
		pregunta5.setItemCaption(0, "Cajas de ahorro o cuenta corriente o plazo fijo");
		pregunta5.addItem(3);
		pregunta5.setItemCaption(3, "Bonos nacionales (renta fija) o fondos que invertían en ellos");
		pregunta5.addItem(6);
		pregunta5.setItemCaption(6, "Acciones (renta variable) o fondos que invertían en ellas");
		pregunta5.addItem(8);
		pregunta5.setItemCaption(8, "Acciones y/o bonos internacionales o fondos que invertían en ellas");

		 pregunta6 = new OptionGroup();
		pregunta6.setHtmlContentAllowed(true);
		pregunta6.addItem(0);
		pregunta6.setItemCaption(0, "Vender todas mis acciones");
		pregunta6.addItem(2);
		pregunta6.setItemCaption(2, "Vender parte de mis acciones");
		pregunta6.addItem(5);
		pregunta6.setItemCaption(5, "No hacer nada");
		pregunta6.addItem(8);
		pregunta6.setItemCaption(8, "Comprar más acciones");
		
		 pregunta7 = new OptionGroup();
		pregunta7.setHtmlContentAllowed(true);
		pregunta7.addItem(0);
		pregunta7.setItemCaption(0, "A                             7,2%                 16,3%                   -5,6% ");
		pregunta7.addItem(3);
		pregunta7.setItemCaption(3, "A                             7,2%                 16,3%                   -5,6% ");
		pregunta7.addItem(6);
		pregunta7.setItemCaption(6, "A                             7,2%                 16,3%                   -5,6% ");
		pregunta7.addItem(8);
		pregunta7.setItemCaption(8, "A                             7,2%                 16,3%                   -5,6% ");
		pregunta7.addItem(10);
		pregunta7.setItemCaption(10, "A                             7,2%                 16,3%                   -5,6% ");

		form.addComponent(pregunta1);
		form.addComponent(pregunta2);
		form.addComponent(pregunta3);
		form.addComponent(pregunta4);
		form.addComponent(pregunta5);
		form.addComponent(new Label("Considere el siguiente escenario. Imagine que en los últimos 3 meses, el <br>"
				            + "mercado de acciones en su conjunto perdió el 25% de su valor. A su vez, una <br>"
				            + "acción en particular que usted tenía también perdió el mísmo porcentaje. <br>"
				            + "¿Qué haría usted?",ContentMode.HTML));
		form.addComponent(pregunta6);
		form.addComponent(new Label("Considere la siguiente tabla. En ella, se detallan inversiones hipotéticas; <br>"
		+ "para cada una	figura el promedio de los rendimientos obtenidos en el período de 1 año y <br>"
		+ "su peor y mejor escenario. ¿Con cual inversión se sentiría usted más cómodo?",ContentMode.HTML));
		form.addComponent(pregunta7);
		panelForm.setContent(form);
		form.setWidth("500px");
		panelForm.setWidth("100%");
		panelForm.setHeight("480px");
		return panelForm;
	}
	
	
	@Override
	protected void pressOk() {
		// TODO Auto-generated method stub
		try{
		Integer HorizonteTemporal = Integer.parseInt(pregunta1.getValue().toString()) +
				Integer.parseInt(pregunta1.getValue().toString());
		Integer ToleranciaRiesgo = Integer.parseInt(pregunta2.getValue().toString())+
				Integer.parseInt(pregunta3.getValue().toString())+
				Integer.parseInt(pregunta4.getValue().toString())+
				Integer.parseInt(pregunta5.getValue().toString())+
				Integer.parseInt(pregunta6.getValue().toString())+
				Integer.parseInt(pregunta7.getValue().toString());
		UsuarioDetalle usuarioDetalle = ((DashboardUI) UI.getCurrent()).getUser();
		usuarioDetalle.setPerfil(usuarioDetalle.setPerfilDeInversor(HorizonteTemporal,ToleranciaRiesgo));
		HibernateUtil.saveEntity(usuarioDetalle);
		
		}catch (Exception e){
			Notification.show("Datos incompletos",Type.WARNING_MESSAGE);
		}
		
	}
}
