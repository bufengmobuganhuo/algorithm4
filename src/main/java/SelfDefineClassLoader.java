/**
 * @author yu zhang
 */
public class SelfDefineClassLoader extends ClassLoader{
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return loadClass(name, false);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            // 先从本地找是否存在该类，如果存在，就返回
            Class<?> clz = findLoadedClass(name);
            if (clz == null) {
                long t0 = System.nanoTime();
                // 先从当前累加
                clz = findClass(name);
                if (clz == null && getParent() != null) {
                    clz = this.getParent().loadClass(name);
                }
            }
            if (resolve) {
                resolveClass(clz);
            }
        }
        return super.loadClass(name, resolve);
    }
}
