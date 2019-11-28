package com.thtf.leanpackage.data_structure_package;

/**
 * @author LiShiChuang
 * @packageName com.thtf.leanpackage.data_structure_package
 * @date 2019-11-27 17:25
 * @描述
 */
public abstract class SuperOrExtendDemo {
    static class SuperDTO<T> {
        private T itemSuoer;

        public SuperDTO(T t) {
            itemSuoer = t;
        }

        public void set(T t) {
            itemSuoer = t;
        }

        public T get() {
            return itemSuoer;
        }
    }

    static class ExtendDTO<T> {
        private T itemExtend;

        public ExtendDTO(T t) {
            itemExtend = t;
        }

        public void set(T t) {
            itemExtend = t;
        }

        public T get() {
            return itemExtend;
        }
    }

    public static void main(String[] args) {
        ExtendDTO<? extends BUS_TYPE> extendDTO = new ExtendDTO<BUS_TYPE>(new BUS_SUN());  //上通界 可读不可写
        BUS_TYPE bus_type = extendDTO.get();
        System.out.println("Extend BUX_TYPE");
        System.out.println("名字->" + bus_type.getName() + "\n年龄->" + bus_type.getAge());
        System.out.println("--------------------------------------------------------------");
        SuperDTO<? super BUS_TYPE> superDTO = new SuperDTO<>(new BUS_TYPE()); //下通界  可写不可读
        BUS_SUN stdu = new BUS_SUN();
        stdu.setAge(11);
        stdu.setName("闯");
        superDTO.set(stdu);
        Object getSuper = superDTO.get();
        System.out.println("Super BUX_TYPE");
//        System.out.println("名字->" + getSuper.getName() + "\n年龄->" + getSuper.getAge());
        System.out.println("--------------------------------------------------------------");
    }

}
