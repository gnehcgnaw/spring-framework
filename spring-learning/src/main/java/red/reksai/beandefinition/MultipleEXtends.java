package red.reksai.beandefinition;



/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/11/19 11:36
 */
public class MultipleEXtends {
	public static void main(String[] args) {
		RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
		rootBeanDefinition.getSource();
	}
}

interface  BeanDefinition extends AttributeAccessor , BeanMetadataElement{

	@Override
	Object getSource();

	@Override
	void setAttribute(String name, Object value);

	@Override
	Object getAttribute(String name);
}

interface  AttributeAccessor{
	Object getSource();
}

interface  BeanMetadataElement{
	Object getSource();

	void setAttribute(String name, Object value);

	Object getAttribute(String name);

}

class RootBeanDefinition implements  BeanDefinition{

	@Override
	public Object getSource() {
		return "1";
	}

	@Override
	public void setAttribute(String name, Object value) {

	}

	@Override
	public Object getAttribute(String name) {
		return null;
	}
}