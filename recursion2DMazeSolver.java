package recursionMaze;

class recursion2DMazeSolver {
  public static final String BLUE = "\u001B[34m"; //Blue Colour code to change ASCII text colour 
  public static final String RESET = "\u001B[0m"; //Code to reset the ASCII colour back to white
  
  public static void main(String[] args) {

     System.out.println("-----------------------Maze Solver-----------------------");

    //Maze 1. This maze has one path to the solution, but also has a dead end path
    String maze1[][] = { {"1", "1", "1", "1", "1", "1", "1", "1", "9", "1"}, //2d array storing first maze
                          {"1", "1", "1", "1", "1", "1", "0", "0", "0", "1"},
                          {"1", "1", "1", "0", "0", "0", "0", "1", "1", "1"},
                          {"1", "1", "1", "0", "1", "1", "1", "0", "1", "1"},
                          {"1", "1", "1", "0", "1", "1", "1", "0", "1", "1"},
                          {"1", "1", "1", "0", "1", "0", "1", "0", "1", "1"},
                          {"1", "0", "0", "0", "0", "1", "1", "0", "1", "1"},
                          {"1", "2", "1", "0", "0", "0", "0", "0", "1", "1"} };

    System.out.println("FIRST MAZE:");

    displayMaze(maze1, 0 ,0); //display the first maze to the user
    System.out.println();
    
    if(solve(maze1, 1, 7)){ //check to see if the maze is solvable 
      System.out.println("Solution found:"); 
      displayMaze(maze1, 0, 0); //display the maze with the path found 
      
    }
    else{
      System.out.print("No solution found"); //Display that there is no solution 
      displayMaze(maze1, 0, 0); //show the maze with he paths that the method checked 
    }

    System.out.println("--------------------------------------------------");


    System.out.println();
     //Maze 2. This maze has multiple paths to the solution, but also includes dead ends along the way
     String maze2[][] = {  {"1", "1", "1", "0", "0", "0", "0", "0", "9", "1"}, //2d array storing second maze
                           {"1", "1", "1", "0", "1", "1", "1", "1", "0", "1"},
                           {"1", "1", "0", "0", "1", "1", "1", "1", "0", "1"},
                           {"1", "1", "0", "0", "0", "0", "0", "0", "0", "1"},
                           {"0", "0", "0", "1", "1", "1", "1", "1", "1", "1"},
                           {"0", "1", "0", "0", "0", "0", "1", "1", "1", "1"},
                           {"0", "1", "0", "1", "1", "1", "1", "1", "1", "1"},
                           {"0", "2", "0", "1", "1", "1", "1", "1", "1", "1"} };

    System.out.println("SECOND MAZE:");
    displayMaze(maze2, 0, 0); //display the second array to the user
    System.out.println();
    
    if(solve(maze2, 1, 7)){ //check if the array is solvable 
      System.out.println("Solution found");
      displayMaze(maze2, 0, 0); //display the maze with the path found 
    }
    else{
      System.out.print("No solution found"); //Display that there is no solution 
      displayMaze(maze2, 0, 0); //show the maze with the paths that the method checked
    } 

    System.out.println("--------------------------------------------------");
    
     System.out.println();
     //Maze 3. This maze has open paths, none of which lead to the end goal
     String maze3[][] = {  {"1", "1", "1", "0", "0", "0", "1", "1", "9", "1"}, //2d array storing third maze
                           {"1", "1", "1", "0", "1", "1", "1", "1", "1", "1"},
                           {"1", "0", "0", "0", "1", "1", "1", "1", "0", "1"},
                           {"0", "0", "1", "1", "0", "0", "1", "0", "0", "1"},
                           {"0", "1", "1", "1", "0", "1", "1", "0", "1", "1"},
                           {"0", "0", "0", "0", "0", "1", "0", "0", "1", "1"},
                           {"0", "0", "1", "1", "1", "0", "0", "1", "1", "1"},
                           {"0", "2", "0", "0", "0", "0", "1", "1", "1", "1"} };

    System.out.println("Third maze:");
    displayMaze(maze3, 0, 0); //display the third maze to the user 
    System.out.println();
    
    if(solve(maze3, 1, 7)){ //check if the maze is solvable 
      System.out.println("Solution found");
      displayMaze(maze3, 0, 0); //display the maze with the path found 
    }
    else{
      System.out.println("No solution found"); // display that there is no solution found
      displayMaze(maze3, 0, 0); //display the maze with the paths that the method checked
    }

     System.out.println("--------------------------------------------------");

  }

  /* 
    Method: displayMaze
    Method to display a 2d array (maze) recursively. this function is a void function that takes the maze, start row, and start collumn as parameters then displays the given 2d array back to the user. 
  */ 
  public static void displayMaze(String[][] maze, int rows, int columns){ 
    if(rows < maze.length){ //base case 
      if(columns < maze[rows].length){ //go through the collumn for the current row
        System.out.print(maze[rows][columns] + " ");
        columns++;
        displayMaze(maze, rows, columns); //recursively call the function with the next collumn
      }
      else{ //general case
        System.out.println();
        rows++; 
        displayMaze(maze, rows, 0); //recursively call the function with the next row
      } 
    }
  }

  /* 
  Method: checkIfOpen
    Boolean method that checks if a given position on the maze is available to move too. The method takes the maze, x coordinate, and Y coordinate as the parameters, and outputs true if the given coordinate is available, or false, if the given coordinate is unavailable. 

  */ 
  public static boolean checkIfOpen(String[][] maze, int x, int y){
    if(y > 7 || y < 0 || x < 0 || x > 9){ //Check if any of the given coordinates are on the border of the maze
      return false;
    }
    else if(maze[y][x] == "0" || maze[y][x] == "9"){ //Check if the given coordinates are a valid position in the given maze 
      return true;
    }
    return false; 
  }
  
  /* 
  Method: solve
    Recursive boolean method that can determine if a maze is solvable, and find a possible solution. This method takes a 2d array (maze), starting x coordinate, and starting y coordinate as the parameters. The function will return true if the given maze is solvable, or false if the given array is not solvable. 

  */ 
  public static boolean solve(String[][]maze, int x, int y){
    
    if(x == 8 && y == 0){ //Check if the coordinates given is the end point (base case_)
      return true;
    }

    //Go right (General Case)
    if(checkIfOpen(maze, x+1 , y)){ //Check if the position to the right is open
      maze[y][x+1] = BLUE + maze[y][x+1] + RESET; //change the colour of the character to the right 
      if(solve(maze,x+1, y)){ //recursively call the function with the position to the right
        return true;
      }
      maze[y][x+1] = "X"; //if the position to the right is not in the correct path, replace the 0 with "X"
    }
    
    //Go left (General Case)
    if(checkIfOpen(maze, x - 1 , y)){ //Check if the position to the left is open
      maze[y][x-1] = BLUE + maze[y][x-1] + RESET; //Change the colour of the position to the left
      if(solve(maze, x - 1, y)){ //Recursively call the function with the coordinates to the left
        return true;
      }
      maze[y][x-1] = "X"; //if the position to the left is not in the correct path, replace the 0 with "X"
              
    }

    //Go up (General Case)
    if(checkIfOpen(maze, x , y - 1)){ //Check if the position above is open
      maze[y-1][x] = BLUE + maze[y-1][x] + RESET; //Change the colour of the coordinates above
      if(solve(maze,x, y - 1)){ //Recursively call the function with the coordinates above
        return true;
      }
      maze[y-1][x] = "X"; //if the position below is not apart of the correct path, replace the 0 with an "X"
      
    }

    //Go down (General Case)
    if(checkIfOpen(maze, x , y + 1)){ //Check if the position below is open
      maze[y+1][x] = BLUE + maze[y+1][x] + RESET; //Change the colour of the coordinates below
      if(solve(maze, x, y + 1)){ //Recursively call the function with the coordinates below
        return true;
      }
      maze[y+1][x] = "X"; //if the position below is not apart of the correct path, replace the 0 with an "X"
              
    }
    return false; //Either cause the code to "back-track" to another path, or show that the maze has no solution
  }
  
  }