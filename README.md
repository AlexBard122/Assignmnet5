# Assignmnet5

Team Members: Alex Bard & Devin Chamberlain

Task Distribution:
Alex:   2 & 4
Devin:  1 & 3
Note:   We will likely collaborate on 3. Need to discuss later.

Tasks:

  1: Read reports into a binary search tree based on start time. Each node must have 2 additional fields to keep track
     of its left and right node amounts.
     Note: One binary tree per state
     Note: Not allowed to read reports into a different data structure before creating the trees
     Note: If two reports have the same date, the one you insert will be the right child
  
  2: After making trees, have user input state and date parameters and calculate the number of reports on and after that date 
     (using the two extra data fields mentioned in task one).

  3: Implement another method which ignores the two extra fields and uses a recursive method to calculate the number of
     reports (same problem and result, different solution).

  4: Make a PDF file showing the runtime for sub-tasks & their complexity order.
     Note: also justify which of the methods created in tasks 2 and 3 are better and should be used in the final product

Answer to question posed in Task 3:

The approach of using a recursive method to find the number of children rather than keeping that information in seperate 
data fields is superior as the latter method increases both the space and runtime of code whereas a recursive method adds 
no space and requires no extra operations to update number of children at each addition. This also greatly reduces the 
complexity of the code, reducing the chances for errors.
