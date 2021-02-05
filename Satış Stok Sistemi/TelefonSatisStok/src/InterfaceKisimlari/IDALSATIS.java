package InterfaceKisimlari;

import SatisArsivi.SatisListesiGetSet;

public interface IDALSATIS<T> {
	
		public void add(T object);
		public void delete(T object);
		public void update(SatisListesiGetSet k);

	

}
