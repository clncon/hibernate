package cn.itcast.hibernate.k_hbm_extends_2;

public class Topic extends Article{
        private int type;
        
        public Topic(){}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}
        
        
}
