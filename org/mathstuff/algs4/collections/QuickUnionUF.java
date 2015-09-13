package org.mathstuff.algs4.collections;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class QuickUnionUF implements UF {

	private int[] elems;

	private int[] rank;
   
  	// private int[] size; // to use union by size instead of rank
   
   	private int[] rs;

	public QuickUnionUF(int n) {

		elems = new int[n];
		rank = new int[n];
      	size = new int[n];
      	rs = rank; // Chooses trees by size

		for (int k = 0; k < n; k++){
			elems[k] = k;
		   // size[k] = 1; // uses size not rank
	   }
   }
   
   
   /* Without path compression
   private int find(int m) {
      if (elems[m] != m)
         return find(elems[m]);
      return m;
   }
   */
   
   /**
    * When a find is called the parent pointer on every ancestor of m is rewired to 
    * point to the root. This is path compression.
    */ 
	private int find(int m) {
		if (elems[m] != m)
			elems[m] = find(elems[m]);
		return elems[m];
	}

   
	@Override
	public boolean connected(int m, int n) {
		return find(m) == find(n);
	}

	@Override
	public void union(int m, int n) {
		int rootn = find(n), rootm = find(m);
		if (rootm == rootn)
			return;
		if (rs[rootm] < rs[rootn]){
			elems[rootm] = rootn;
         	// size[rootn] = size[rootn] + size[rootm];
		} else if (rs[rootn] < rs[rootm]) {
			elems[rootn] = rootm;
         	// size[rootm] = size[rootn] + size[rootm];
		} else {
			elems[rootn] = rootm;
			rank[rootm] = rank[rootn] + 1;
         	// size[rootm] = size[rootn] + size[rootm];
		}
	}

	@Override
	public String toString() {
		//@formatter:off
		Map<Object, java.util.Set<Integer>> grouped =
				IntStream.range(0,elems.length)
				      .boxed()
					  .collect(Collectors.groupingBy(
							m -> find(m), Collectors.toSet()));

		return grouped.values().toString();
	}
   
	public void show() {
		System.out.println(Arrays.toString(elems));
		System.out.println(Arrays.toString(rank));

	}
   
   public static void main(String[] args) {
      UF uf = new QuickUnionUF(17);
      // 0-4 7-3 9-1 5-0 8-6 8-3 8-2 9-0 3-1 
      uf.union(1,2);
      uf.union(3,4);
      uf.union(1,3);
      uf.union(5,6);
      uf.union(7,8);
      uf.union(5,7);
      uf.union(1,5);
      uf.union(1+8,2+8);
      uf.union(3+8,4+8);
      uf.union(1+8,3+8);
      uf.union(5+8,6+8);
      uf.union(7+8,8+8);
      uf.union(5+8,7+8);
      uf.union(1+8,5+8);
      uf.union(1,9);
      uf.connected(16,1);
      uf.connected(8,1);
   }

}
