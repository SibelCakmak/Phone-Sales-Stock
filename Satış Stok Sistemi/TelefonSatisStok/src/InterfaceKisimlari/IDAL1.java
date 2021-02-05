package InterfaceKisimlari;

import OturumBilgileri.OturumBilgileriGetSet;

public interface IDAL1<T> {

	public void add(T object);
	public void delete(T object);
	public void update(OturumBilgileriGetSet k);

}
