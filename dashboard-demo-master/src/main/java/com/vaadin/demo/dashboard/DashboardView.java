/**
 * DISCLAIMER
 * 
 * The quality of the code is such that you should not copy any of it as best
 * practice how to build Vaadin applications.
 * 
 * @author jouni@vaadin.com
 * 
 */

package com.vaadin.demo.dashboard;

import graficos.ChartComposicionCartera;
import graficos.ChartComposicionCarteraMoneda;
import graficos.ChartGanancias;

import java.text.DecimalFormat;
import java.util.List;

import utils.HibernateUtil;
import vistas.ModalTestDeInversor;

import com.vaadin.data.Property;
import com.vaadin.demo.domain.Notificacion;
import com.vaadin.demo.domain.UsuarioDetalle;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.Table.RowHeaderMode;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class DashboardView extends VerticalLayout implements View {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4071854876711456470L;
	Table t;
	private List<Notificacion> notificaciones;
	private Integer notifiacionesCant;

    public DashboardView() {
        setSizeFull();
        addStyleName("dashboard-view");

        HorizontalLayout top = new HorizontalLayout();
        top.setWidth("100%");
        top.setSpacing(true);
        top.addStyleName("toolbar");
        addComponent(top);
        String saludo = ((DashboardUI)UI.getCurrent()).getUser().getSaludo();
        final Label title = new Label(saludo == null ? "Bienvenido " +((DashboardUI)UI.getCurrent()).getUser().getNombre() : saludo );
        title.setSizeUndefined();
        title.addStyleName("h1");
        top.addComponent(title);
        top.setComponentAlignment(title, Alignment.MIDDLE_LEFT);
        top.setExpandRatio(title, 1);

         notificaciones = ((DashboardUI)UI.getCurrent()).getUser().obtenerNotificacion();
         notifiacionesCant = notificaciones.size();
        Button notify = new Button(notifiacionesCant == 0 ? "":notifiacionesCant.toString());
         
         notify.setDescription("Notificaciones ("+notifiacionesCant+" no leidas)");
        // notify.addStyleName("borderless");
        notify.addStyleName("notifications");
        notify.addStyleName("unread");
        if (notifiacionesCant == 0 )   notify.removeStyleName("unread");
        notify.addStyleName("icon-only");
        notify.addStyleName("icon-bell");
        notify.addClickListener(new ClickListener() {
            /**
			 * 
			 */
			private static final long serialVersionUID = -3250568494372269492L;

			@Override
            public void buttonClick(ClickEvent event) {
                ((DashboardUI) getUI()).clearDashboardButtonBadge();
                event.getButton().removeStyleName("unread");
                event.getButton().setDescription("Notifications");
                for(Notificacion n : notificaciones){
                	n.setVista(true);
                	try {
						HibernateUtil.saveEntity(n);
					} catch (Exception e) {
						System.out.println("No se pueden marcar las notificaciones");
						e.printStackTrace();
					}
                }
                if (notifications != null && notifications.getUI() != null)
                    notifications.close();
                else {
                    buildNotifications(event);
                    getUI().addWindow(notifications);
                    notifications.focus();
                    ((CssLayout) getUI().getContent())
                            .addLayoutClickListener(new LayoutClickListener() {
                                /**
								 * 
								 */
								private static final long serialVersionUID = -6067867129998245551L;

								@Override
                                public void layoutClick(LayoutClickEvent event) {
                                    notifications.close();
                                    ((CssLayout) getUI().getContent())
                                            .removeLayoutClickListener(this);
                                }
                            });
                }

            }
        });
        top.addComponent(notify);
        top.setComponentAlignment(notify, Alignment.MIDDLE_LEFT);

        Button editConf = new Button();
        editConf.addStyleName("icon-edit");
        editConf.addStyleName("icon-only");
        top.addComponent(editConf);
        editConf.setDescription("Edit Dashboard");
        editConf.addClickListener(new ClickListener() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 6213644941001760982L;

			@Override
            public void buttonClick(ClickEvent event) {
                final Window w = new Window("Saludo");

                w.setModal(true);
                w.setClosable(false);
                w.setResizable(false);
                w.addStyleName("edit-dashboard");

                getUI().addWindow(w);

                w.setContent(new VerticalLayout() {
                    /**
					 * 
					 */
					private static final long serialVersionUID = -4664796188884801858L;
					TextField name = new TextField("Saludo");
                    {
                        addComponent(new FormLayout() {
                            {
                                setSizeUndefined();
                                setMargin(true);
                                name.setValue(title.getValue());
                                addComponent(name);
                                name.focus();
                                name.selectAll();
                            }
                        });

                        addComponent(new HorizontalLayout() {
                            {
                                setMargin(true);
                                setSpacing(true);
                                addStyleName("footer");
                                setWidth("100%");

                                Button cancel = new Button("Cancelar");
                                cancel.addClickListener(new ClickListener() {
                                    @Override
                                    public void buttonClick(ClickEvent event) {
                                        w.close();
                                    }
                                });
                                cancel.setClickShortcut(KeyCode.ESCAPE, null);
                                addComponent(cancel);
                                setExpandRatio(cancel, 1);
                                setComponentAlignment(cancel,
                                        Alignment.TOP_RIGHT);

                                Button ok = new Button("Guardar");
                                ok.addStyleName("wide");
                                ok.addStyleName("default");
                                ok.addClickListener(new ClickListener() {
                                    @Override
                                    public void buttonClick(ClickEvent event) {
                                        UsuarioDetalle  usuario = ((DashboardUI)UI.getCurrent()).getUser();
                                        usuario.setSaludo(name.getValue());
                                        try {
                                        	System.err.println(name.getValue());
											HibernateUtil.saveEntity(usuario);
										} catch (Exception e) {
											Notification.show("No se puedo guardar el saludo", Type.HUMANIZED_MESSAGE);
										}
                                    	title.setValue(name.getValue());
                                        w.close();
                                    }
                                });
                                ok.setClickShortcut(KeyCode.ENTER, null);
                                addComponent(ok);
                            }
                        });

                    }
                });

            }
        });
        Button edit = new Button();
        edit.addStyleName("icon-config");
        edit.addStyleName("icon-only");
        //top.addComponent(edit);
        edit.setDescription("Test de Inversor");
        edit.addClickListener(new ClickListener() {
            /**
			 * 
			 */
			private static final long serialVersionUID = -3465630020140049816L;

			@Override
            public void buttonClick(ClickEvent event) {
               

                getUI().addWindow(new ModalTestDeInversor());

               

            }
        });
        top.setComponentAlignment(editConf, Alignment.MIDDLE_LEFT);

        HorizontalLayout row = new HorizontalLayout();
        row.setSizeFull();
        row.setMargin(new MarginInfo(true, true, false, true));
        row.setSpacing(true);
        addComponent(row);
        setExpandRatio(row, 1.5f);

        row.addComponent(createPanel(new ChartGanancias().getChart()));

        TextArea notes = new TextArea("Notas");
        notes.setValue(((DashboardUI)UI.getCurrent()).getUser().getNota());
        notes.setSizeFull();
        notes.addTextChangeListener(new TextChangeListener() {
			
			@Override
			public void textChange(TextChangeEvent event) {
				UsuarioDetalle usuario =((DashboardUI)UI.getCurrent()).getUser();
				System.err.println(event.getText());
				usuario.setNota(event.getText());
				try {
					HibernateUtil.saveEntity(usuario);
				} catch (Exception e) {
					System.out.println("No guardo la nota");
					e.printStackTrace();
				}
				
			}
		});;
        CssLayout panel = createPanel(notes);
        panel.addStyleName("notes");
        row.addComponent(panel);

        row = new HorizontalLayout();
        row.setMargin(true);
        row.setSizeFull();
        row.setSpacing(true);
        addComponent(row);
        setExpandRatio(row, 2);

        t = new Table() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 2180293203977101507L;

			@Override
            protected String formatPropertyValue(Object rowId, Object colId,
                    Property<?> property) {
                if (colId.equals("Revenue")) {
                    if (property != null && property.getValue() != null) {
                        Double r = (Double) property.getValue();
                        String ret = new DecimalFormat("#.##").format(r);
                        return "$" + ret;
                    } else {
                        return "";
                    }
                }
                return super.formatPropertyValue(rowId, colId, property);
            }
        };
        row.addComponent(createPanel(new ChartComposicionCarteraMoneda().getChart()));
        row.addComponent(createPanel(new ChartComposicionCartera().getChart()));

    }

    private CssLayout createPanel(Component content) {
        CssLayout panel = new CssLayout();
        panel.addStyleName("layout-panel");
        panel.setSizeFull();

        Button configure = new Button();
        configure.addStyleName("configure");
        configure.addStyleName("icon-cog");
        configure.addStyleName("icon-only");
        configure.addStyleName("borderless");
        configure.setDescription("Configure");
        configure.addStyleName("small");
        configure.addClickListener(new ClickListener() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 7437776387499650365L;

			@Override
            public void buttonClick(ClickEvent event) {
                Notification.show("No Implementado");
            }
        });
        panel.addComponent(configure);

        panel.addComponent(content);
        return panel;
    }

    @Override
    public void enter(ViewChangeEvent event) {
      //  DataProvider dataProvider = ((DashboardUI) getUI()).dataProvider;
      //  t.setContainerDataSource(dataProvider.getRevenueByTitle());
    }

    Window notifications;

    private void buildNotifications(ClickEvent event) {
        notifications = new Window("Notificaciones");
        VerticalLayout l = new VerticalLayout();
        l.setMargin(true);
        l.setSpacing(true);
        notifications.setContent(l);
        notifications.setWidth("300px");
        notifications.addStyleName("notifications");
        notifications.setClosable(false);
        notifications.setResizable(false);
        notifications.setDraggable(false);
        notifications.setPositionX(event.getClientX() - event.getRelativeX());
        notifications.setPositionY(event.getClientY() - event.getRelativeY());
        notifications.setCloseShortcut(KeyCode.ESCAPE, null);
for (Notificacion n : notificaciones){
        Label label = new Label(
                "<hr><b>"
                     //   + Generator.randomFirstName()
                        + " "
                      //  + Generator.randomLastName()
                        + n.getMensaje()+ "</b>"+n.getDescripcion()+"<br><span>"+n.getFecha()+"</span><br>"
                       // + Generator.randomText(18)
                        , ContentMode.HTML);
        l.addComponent(label);
}
     }

}
