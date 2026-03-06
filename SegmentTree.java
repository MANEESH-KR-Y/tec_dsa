class SegmentTree {

    int[] tree;
    int n;

    public SegmentTree(int[] arr) {
        n = arr.length;
        tree = new int[4*n];
        build(arr,0,0,n-1);
    }

    private void build(int[] arr,int node,int start,int end) {

        if(start == end)
            tree[node] = arr[start];

        else {

            int mid = (start+end)/2;

            build(arr,2*node+1,start,mid);
            build(arr,2*node+2,mid+1,end);

            tree[node] = Math.max(tree[2*node+1], tree[2*node+2]);
        }
    }

    public void update(int idx,int val) {
        updateUtil(0,0,n-1,idx,val);
    }

    private void updateUtil(int node,int start,int end,int idx,int val) {

        if(start == end)
            tree[node] = val;

        else {

            int mid = (start+end)/2;

            if(idx <= mid)
                updateUtil(2*node+1,start,mid,idx,val);
            else
                updateUtil(2*node+2,mid+1,end,idx,val);

            tree[node] = Math.max(tree[2*node+1], tree[2*node+2]);
        }
    }

    public int queryMax(int L,int R) {
        return query(0,0,n-1,L,R);
    }

    private int query(int node,int start,int end,int L,int R) {

        if(R < start || end < L)
            return Integer.MIN_VALUE;

        if(L <= start && end <= R)
            return tree[node];

        int mid = (start+end)/2;

        return Math.max(
                query(2*node+1,start,mid,L,R),
                query(2*node+2,mid+1,end,L,R)
        );
    }
}
