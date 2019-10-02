package come.algos;

public class Topath {
	public static void main(String[] args) {

		
		//相对路径转为绝对路径
		String path= "/home/abs/../temp/new/.././sy";
		String[] PathArray= path.split("/");
		
		MyLink list=new MyLink();
		String ss = "0";
		for (String str :PathArray) {
			if(str.equals("..")) {      //不能用==
				ss  = list.deleCurr();  //返回最后一个节点
				System.out.print(ss);
			}else if(str.equals(".")) {
				
			}else {
				list.add(str);
			}
		}
		System.out.print("\n");
		list.printList();
		
	}
	

	static class Node{
		Node next = null;
		String str;
		public Node() {
			
		}
		public Node(String str) {
			this.str = str;
		}

	}
	public static class MyLink{
		//头节点
		Node head = null;
		//尾节点
		Node last;
		//链表的大小
		int size;
		
		public MyLink() {
			head = new Node(); //实例化头节点
			last = head;	//令头节点等于尾节点
		}
	
		public void add(String str) {
			Node  node = new Node(str);
			last.next = node; //尾节点后加节点
			last = node ;
			size++;
		}
		public String deleCurr() {
			Node node = head;
			//获取倒数第二个节点
			for(int i =0;i<size-1;i++) {
				node = node.next;
			}
			
			//删除最后一个节点
			node.next = null;
			last = node;
			//倒数第二个节点设置为尾节点
			size--;
			return last.str;
		}
		

		public void printList() {
			Node node = head;
			for (int i = 0;i<size;i++) {
				node = node.next;
				System.out.print(node.str+"/");
			}
		}
		//删除当前节点
		public void deleNode(int index) {
			
			
		}
	}
	

}
