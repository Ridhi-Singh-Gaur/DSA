// 🚀 Java Sorting Cheat Sheet (Comparators)


// 📌 1. 1D Array Sorting
// ✅ Ascending
Arrays.sort(arr);

// ✅ Descending
Arrays.sort(arr, (a, b) -> Integer.compare(b, a));


// 📌 2. 2D Array Sorting (int[][])
// ✅ Sort by 1st column (Ascending)
Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

// ✅ Sort by 1st column (Descending)
Arrays.sort(arr, (a, b) -> Integer.compare(b[0], a[0]));

// ✅ Sort by 2nd column (Ascending)
Arrays.sort(arr, (a, b) -> Integer.compare(a[1], b[1]));

// ✅ Sort by 2nd column (Descending)
Arrays.sort(arr, (a, b) -> Integer.compare(b[1], a[1]));


// 📌 3. Multi-Level Sorting (Tie Breaking)
// ✅ 1st column ↑, if equal then 2nd column ↑
Arrays.sort(arr, (a, b) -> {
    if(a[0] != b[0]) {
        return Integer.compare(a[0], b[0]);
    }
    return Integer.compare(a[1], b[1]);
});

// ✅ 1st column ↓, if equal then 2nd column ↑
Arrays.sort(arr, (a, b) -> {
    if(a[0] != b[0]) {
        return Integer.compare(b[0], a[0]);
    }
    return Integer.compare(a[1], b[1]);
});

// ✅ 1st column ↑, if equal then 2nd column ↓
Arrays.sort(arr, (a, b) -> {
    if(a[0] != b[0]) {
        return Integer.compare(a[0], b[0]);
    }
    return Integer.compare(b[1], a[1]);
});


// 📌 4. Custom Object Sorting
// Example Class:
class Pair {
    int a, b;
}

// ✅ Sort by a Ascending
Arrays.sort(arr, (x, y) -> Integer.compare(x.a, y.a));

// ✅ Sort by b Descending
Arrays.sort(arr, (x, y) -> Integer.compare(y.b, x.b));

// ✅ Multi-level (a ↑ then b ↓)
Arrays.sort(arr, (x, y) -> {
    if(x.a != y.a) {
        return Integer.compare(x.a, y.a);
    }
    return Integer.compare(y.b, x.b);
});


// 📌 5. PriorityQueue (Max Heap / Min Heap)
// ✅ Min Heap (default)
PriorityQueue<Integer> pq = new PriorityQueue<>();

// ✅ Max Heap
PriorityQueue<Integer> pq = new PriorityQueue<>(
    (a, b) -> Integer.compare(b, a)
);

// ✅ Custom Object (Max Heap by value)
PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
    return Integer.compare(b.b, a.b);
});


// ⚠️ Important Tips
// ❌ Avoid this:
b[1] - a[1]   // overflow risk

// ✅ Use this:
Integer.compare(b[1], a[1])


// 🧠 Golden Rule

// 👉 Comparator return:

// Negative → a comes first
