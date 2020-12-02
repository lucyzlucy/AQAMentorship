package driver.elements;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.*;
import java.util.List;

public class FieldDecorator extends DefaultFieldDecorator {

    // Simple version - with ONE custom element

//    public FieldDecorator(SearchContext searchContext) {
//        super(new DefaultElementLocatorFactory(searchContext));
//    }
//
//    @Override
//    public Object decorate(ClassLoader loader, Field field) {
//        if (field.getType().equals(WebTable.class)) {
//            ElementLocator locator = factory.createLocator(field);
//            if (locator == null) {
//                return null;
//            }
//            return new WebTable(proxyForLocator(loader, locator));
//        }
//        return super.decorate(loader, field);
//    }


    public FieldDecorator(SearchContext searchContext) {
        super(new DefaultElementLocatorFactory(searchContext));
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Class<CustomElement> decoratableClass = decoratableClass(field);
        if (decoratableClass != null) {
            ElementLocator locator = factory.createLocator(field);
            if (locator == null) {
                return null;
            }

            if (List.class.isAssignableFrom(field.getType())) {
                return createList(loader, locator, decoratableClass);
            }

            return createElement(loader, locator, decoratableClass);
        }
        return super.decorate(loader, field);
    }

    @SuppressWarnings("unchecked")
    private Class<CustomElement> decoratableClass(Field field) {

        Class<?> clazz = field.getType();

        if (List.class.isAssignableFrom(clazz)) {

            if (field.getAnnotation(FindBy.class) == null &&
                    field.getAnnotation(FindBys.class) == null) {
                return null;
            }
            Type genericType = field.getGenericType();
            if (!(genericType instanceof ParameterizedType)) {
                return null;
            }
            clazz = (Class<?>) ((ParameterizedType) genericType).
                    getActualTypeArguments()[0];
        }

        if (CustomElement.class.isAssignableFrom(clazz)) {
            return (Class<CustomElement>) clazz;
        } else {
            return null;
        }
    }

    protected CustomElement createElement(ClassLoader loader,
                                          ElementLocator locator,
                                          Class<CustomElement> clazz) {
        WebElement proxy = proxyForLocator(loader, locator);
        return WrapperFactory.createInstance(clazz, proxy);
    }

    @SuppressWarnings("unchecked")
    protected List<CustomElement> createList(ClassLoader loader,
                                             ElementLocator locator,
                                             Class<CustomElement> clazz) {

        InvocationHandler handler =
                new LocatingCustomElementListHandler(locator, clazz);
        List<CustomElement> elements =
                (List<CustomElement>) Proxy.newProxyInstance(
                        loader, new Class[]{List.class}, handler);
        return elements;
    }
}
