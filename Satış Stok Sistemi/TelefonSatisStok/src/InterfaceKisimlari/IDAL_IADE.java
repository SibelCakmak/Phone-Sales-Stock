package InterfaceKisimlari;

import IadeArsivi.IadeEdilenUrunlerGetSet;

public interface IDAL_IADE<T> {
	public void add(T object);
	public void delete(T object);
	public void update(IadeEdilenUrunlerGetSet k);
}
