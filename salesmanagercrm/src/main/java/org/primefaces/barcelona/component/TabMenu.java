package org.primefaces.barcelona.component;

import org.primefaces.util.ComponentUtils;

import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.component.UIPanel;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ListenerFor;
import javax.faces.event.PostAddToViewEvent;

@ListenerFor(sourceClass = TabMenu.class, systemEventClass = PostAddToViewEvent.class)
public class TabMenu extends UIPanel implements org.primefaces.component.api.Widget {

    public static final String COMPONENT_TYPE = "org.primefaces.component.BarcelonaTabMenu";
    public static final String COMPONENT_FAMILY = "org.primefaces.component";
    public static final String DEFAULT_RENDERER = "org.primefaces.component.BarcelonaTabMenuRenderer";

    private static final String[] LEGACY_RESOURCES = new String[]{"primefaces.css", "jquery/jquery.js", "jquery/jquery-plugins.js", "primefaces.js"};
    private static final String[] MODERN_RESOURCES = new String[]{"components.css", "jquery/jquery.js", "jquery/jquery-plugins.js", "core.js"};

    public TabMenu() {
        setRendererType(DEFAULT_RENDERER);
    }

    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    public java.lang.String getWidgetVar() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.widgetVar, null);
    }

    public void setWidgetVar(java.lang.String _widgetVar) {
        getStateHelper().put(PropertyKeys.widgetVar, _widgetVar);
    }

    public int getActiveIndex() {
        return (java.lang.Integer) getStateHelper().eval(PropertyKeys.activeIndex, 0);
    }

    public void setActiveIndex(int _activeIndex) {
        getStateHelper().put(PropertyKeys.activeIndex, _activeIndex);
    }

    public java.lang.String getOnTabChange() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.onTabChange, null);
    }

    public void setOnTabChange(java.lang.String _onTabChange) {
        getStateHelper().put(PropertyKeys.onTabChange, _onTabChange);
    }

    public java.lang.String getOnTabClose() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.onTabClose, null);
    }

    public void setOnTabClose(java.lang.String _onTabClose) {
        getStateHelper().put(PropertyKeys.onTabClose, _onTabClose);
    }

    public String resolveWidgetVar() {
        return ComponentUtils.resolveWidgetVar(getFacesContext(), this);
    }

    @Override
    public void processEvent(ComponentSystemEvent event) throws AbortProcessingException {
        if (event instanceof PostAddToViewEvent) {
            FacesContext context = getFacesContext();
            UIViewRoot root = context.getViewRoot();

            boolean isPrimeConfig;
            try {
                isPrimeConfig = Class.forName("org.primefaces.config.PrimeConfiguration") != null;
            } catch (ClassNotFoundException e) {
                isPrimeConfig = false;
            }

            String[] resources = (isPrimeConfig) ? MODERN_RESOURCES : LEGACY_RESOURCES;

            for (String res : resources) {
                UIComponent component = context.getApplication().createComponent(UIOutput.COMPONENT_TYPE);
                if (res.endsWith("css")) {
                    component.setRendererType("javax.faces.resource.Stylesheet");
                } else if (res.endsWith("js")) {
                    component.setRendererType("javax.faces.resource.Script");
                }

                component.getAttributes().put("library", "primefaces");
                component.getAttributes().put("name", res);

                root.addComponentResource(context, component);
            }
        }
    }

    public enum PropertyKeys {

        widgetVar,
        activeIndex,
        onTabChange,
        onTabClose;

        String toString;

        PropertyKeys(String toString) {
            this.toString = toString;
        }

        PropertyKeys() {
        }

        public String toString() {
            return ((this.toString != null) ? this.toString : super.toString());
        }
    }
}
