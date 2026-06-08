# Smart Library System — Code Analysis Report

**Repository:** `raihan1205/Smart-Library`
**Language:** Java (console application)
**Domain:** Data Structures course project — Binary Search Tree + Stack

---

## 1. Overview

The Smart Library System is a console-based Java application that manages a book
catalogue and a borrowing history. It is built around two **from-scratch data
structures** (no `java.util` collections):

- A **Binary Search Tree (BST)**, keyed by ISBN, for the book catalogue.
- A **singly-linked-list Stack (LIFO)** for the borrowing history.

Users interact through a numbered menu: add a book, search by ISBN, borrow a book,
view borrowing history, or exit.

---

## 2. Architecture & File Structure

| File | Role | Lines |
|------|------|-------|
| `Main.java` | Entry point — launches the menu loop | 4 |
| `SmartLibrary.java` | Application logic + console UI / input validation | 211 |
| `Book.java` | Book entity (ISBN, title, author) + BST child pointers | 43 |
| `BookBST.java` | Binary Search Tree — `insert` / `search` by ISBN | 44 |
| `BorrowStack.java` | LIFO stack of borrowed books (linked list) | 75 |
| `LibraryADT.java` | Interface defining the abstract library contract | 36 |
| `README.md` | Project description (currently 2 lines) | 2 |

**Layering:** `Main → SmartLibrary → {BookBST, BorrowStack} → Book`. The separation of
entity (`Book`), structures (`BookBST`, `BorrowStack`), and orchestration/UI
(`SmartLibrary`) is clean and appropriate for the project scope.

---

## 3. Data Structures & Complexity

### Binary Search Tree (`BookBST`)
- **Key:** `isbn` (`long`). Recursive `insert` and `search`.
- Duplicate ISBNs are rejected with a message (`BookBST.java:19`).
- **Complexity:** average `O(log n)` for insert/search; **worst case `O(n)`** if ISBNs
  are inserted in sorted order (the tree degrades into a linked list — there is no
  self-balancing such as AVL or Red-Black).

### Stack (`BorrowStack`)
- Custom linked list with a private `Node` inner class (good information hiding).
- **Complexity:** `push` / `pop` / `peek` / `isEmpty` / `size` = `O(1)`; `show` = `O(n)`.
- Correct LIFO behaviour — newest borrow printed first.

---

## 4. Component Analysis

**`SmartLibrary.java`** — Strong input handling: numeric parsing is wrapped in
`try/catch` (`NumberFormatException`), and empty title/author/student-ID are rejected.
The search sub-menu cleanly reports unsupported options instead of crashing.

**`BorrowStack.java`** — The best-documented file. Clear integration notes for
teammates and a clean O(1) API. It correctly avoids `java.util.Stack`, which is the
right call for a data-structures course.

**`Book.java`** — Standard encapsulated entity with getters/setters.

**`BookBST.java`** — Correct recursive BST with the public/private method split
(`insert`/`insertBook`, `search`/`searchBook`) preserving information hiding.

---

## 5. Strengths

- ✅ Data structures implemented **from scratch**, which is the core learning goal.
- ✅ Clean separation of concerns across entity / structure / UI layers.
- ✅ Robust user-input validation (non-numeric input, blank fields).
- ✅ Good in-code documentation, especially the integration notes in `BorrowStack`.
- ✅ Correct algorithmic behaviour for BST search/insert and LIFO history.

---

## 6. Issues & Recommendations

### High priority — correctness / build

1. **`LibraryADT` is declared but never used, and lives in a different package.**
   `LibraryADT.java:1` declares `package smartlibrary;`, while every other file is in
   the default package. `SmartLibrary` does **not** `implements LibraryADT`, and the
   interface signatures don't match the class (e.g. `searchBook(long)` returns `void`
   in the interface but `Book` in `SmartLibrary`). As written, the interface is
   decorative and the package mismatch can break a directory-based compile.
   **Fix:** put all files in the same package, have `SmartLibrary implements LibraryADT`,
   and align the method signatures — or remove the interface if not required.

2. **Leftover demo `main()` in `BorrowStack.java` (lines 63–75).** A comment even says
   *"REMOVE LINES 63-75 WHEN MERGING."* It still ships in the repo.
   **Fix:** delete the demo `main` before submission so the only entry point is `Main`.

3. **`[cite: N]` tags throughout `LibraryADT.java` Javadoc** (e.g. `[cite: 10]`) look
   like leftover artifacts from a document/AI tool and read as unfinished.
   **Fix:** remove them and write clean Javadoc.

### Medium priority — functionality

4. **Borrowing never updates availability.** `borrowBook` only pushes to history; the
   book stays in the catalogue forever (acknowledged at `SmartLibrary.java:37`). A real
   library should mark books unavailable or track copies.

5. **No "return book" path.** `BorrowStack.pop()` exists but is never called from the
   menu, so borrowed books can't be returned through the UI. Add a menu option that
   uses `pop()`.

6. **Search by title / author is unimplemented** (`SmartLibrary.java:200,204`) because
   the BST is keyed only by ISBN. Consider a secondary index (e.g. a hash map keyed by
   title) if those searches are required.

### Low priority — design polish

7. **`Book` leaks BST structure.** `left` / `right` (`Book.java:8-9`) are
   package-private and couple the data entity to the tree. A dedicated private `Node`
   class inside `BookBST` (mirroring `BorrowStack`'s pattern) would be cleaner and keep
   `Book` a pure entity.

8. **Unbalanced BST.** For larger or sorted datasets, consider a self-balancing tree
   (AVL) to guarantee `O(log n)`.

9. **Expand the README.** It is currently two lines. Add build/run instructions,
   feature list, and group member names.

---

## 7. How to Build & Run

```bash
# from the project root (after resolving the package issue in item 1)
javac *.java
java Main
```

---

## 8. Summary

A solid, well-structured data-structures project that correctly implements a BST and a
custom stack with good input validation and documentation. The main items to address
