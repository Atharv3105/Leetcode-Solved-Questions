class Solution {
    // Standard Union-Find structure
    class DSU {
        int[] parent;
        
        public DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i; // Every account starts isolated
            }
        }
        
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }
        
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent[rootX] = rootY;
            }
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        DSU dsu = new DSU(n);
        
        // Map: Email -> Account Index
        Map<String, Integer> emailToAccount = new HashMap<>();
        
        // 1. Map emails to accounts and Union overlapping accounts
        for (int i = 0; i < n; i++) {
            List<String> account = accounts.get(i);
            // Start from j = 1 to skip the name
            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);
                
                if (emailToAccount.containsKey(email)) {
                    // We've seen this email before! Merge the current account (i) 
                    // with the account we previously mapped this email to.
                    dsu.union(i, emailToAccount.get(email));
                } else {
                    emailToAccount.put(email, i);
                }
            }
        }
        
        // 2. Group emails by their root account index
        Map<Integer, List<String>> rootToEmails = new HashMap<>();
        for (String email : emailToAccount.keySet()) {
            int rootAccount = dsu.find(emailToAccount.get(email));
            rootToEmails.computeIfAbsent(rootAccount, x -> new ArrayList<>()).add(email);
        }
        
        // 3. Format the result
        List<List<String>> mergedAccounts = new ArrayList<>();
        for (int rootIndex : rootToEmails.keySet()) {
            List<String> emails = rootToEmails.get(rootIndex);
            Collections.sort(emails); // Sort emails alphabetically
            
            // Get the name from the original accounts list
            String name = accounts.get(rootIndex).get(0);
            
            // Construct the final account representation
            List<String> formattedAccount = new ArrayList<>();
            formattedAccount.add(name);
            formattedAccount.addAll(emails);
            
            mergedAccounts.add(formattedAccount);
        }
        
        return mergedAccounts;
    }
}