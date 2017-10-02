/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cuiqm
 * Date: 2017-09-14
 * Time: 上午11:51
 */
class Test {
    public static void main(String[] args) {
        System.out.println(new B().getValue());
    }

    static class A {
        protected int value;

        public A(int value) {
            setValue(value);
        }

        public int getValue() {
            try {
                value++;
                return value;
            } finally {
                this.setValue(value);
                System.out.println(value);
            }
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    static class B extends A {

        public B(int value) {
            super(value);
        }

        public B() {
            super(5);
            setValue(getValue() - 3);
        }

        public void setValue(int value) {
            super.setValue(value * 2);
        }
    }

}