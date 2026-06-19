package Chapter05.HashTable;

import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(7);

        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("----------------------------------------------");
            System.out.println("add: add an employee");
            System.out.println("show: show information of all employees");
            System.out.println("search: search for an employee");
            System.out.println("delete: delete an employee");
            System.out.println("exit: exit");
            System.out.println("----------------------------------------------");
            System.out.print("Enter key: ");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.print("Enter employee id: ");
                    int employeeId = scanner.nextInt();
                    System.out.print("Enter employee name: ");
                    String name = scanner.next();
                    Employee employee = new Employee(employeeId, name);
                    hashTable.addEmployee(employee);
                    break;
                case "show":
                    System.out.println("employee list:");
                    hashTable.showAllEmployees();
                    break;
                case "search":
                    System.out.println("Enter employee id: ");
                    int searchId = scanner.nextInt();
                    hashTable.showEmployeeById(searchId);
                    break;
                case "delete":
                    System.out.print("Enter employee id: ");
                    int removeId = scanner.nextInt();
                    hashTable.removeEmployeeById(removeId);
                    break;
                case "exit":
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid key");
                    break;
            }
        }
    }
}

class HashTable {
    private EmployeeLinkedList[] employeesLists;
    private int size; // 表示链表的数量

    public HashTable(int size) {
        this.size = size;
        employeesLists = new EmployeeLinkedList[size];
        for (int i = 0; i < size; i++) {
            employeesLists[i] = new EmployeeLinkedList();
        }
    }

    public void addEmployee(Employee employee) {
        //根据员工的id，得到该员工应该添加到某条链表
        int employeeLinkedListNo = hashFunction(employee.getId());
        employeesLists[employeeLinkedListNo].addEmployee(employee);
    }

    public void showAllEmployees() {
        int counter = 1;
        for (EmployeeLinkedList employeesList : employeesLists) {
            System.out.printf("The %d employees List: \n", counter++);
            employeesList.showEmployees();
            System.out.println("-----------------");
        }
    }

    public void showEmployeeById(int employeeId) {
        int employeeLinkedListNo = hashFunction(employeeId);
        Employee targetEmployee = employeesLists[employeeLinkedListNo].showEmployeeById(employeeId);
        if (targetEmployee == null) {
            System.out.println("The employee with id " + employeeId + " does not exist");
        }
        else {
            System.out.println("The employee information : " + targetEmployee);
        }
    }

    public void removeEmployeeById(int employeeId) {
        int employeeLinkedListNo = hashFunction(employeeId);
        employeesLists[employeeLinkedListNo].removeEmployeeById(employeeId);
    }

    //编写一个散列函数，使用简单的取模法
    public int hashFunction(int id){
        return id % size;
    }

    public EmployeeLinkedList[] getEmployeesLists() {
        return employeesLists;
    }

    public int getSize() {
        return size;
    }

    public void setEmployeesLists(EmployeeLinkedList[] employeesLists) {
        this.employeesLists = employeesLists;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

class Employee {
    private int id;
    private String name;
    private Employee next; //默认为null

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getNext() {
        return next;
    }

    public void setNext(Employee next) {
        this.next = next;
    }

    public String toString(){
        return "Employee [id=" + id + ", name=" + name + "]";
    }
}

class EmployeeLinkedList {
    private Employee head; //默认为null

    //添加雇员，id时自增长，即id的分配总是从小到大
    public void addEmployee(Employee employee) {
        //如果是添加第一个雇员，
        if (head == null) {
            head = employee;
            return;
        }
        //如果不是添加第一个雇员，则使用一个辅助的指针，帮助定位到最后
        Employee currentEmployee = head;
        while (currentEmployee.getNext() != null) {
            currentEmployee = currentEmployee.getNext();
        }
        currentEmployee.setNext(employee);
    }

    //遍历链表的雇员信息
    public void showEmployees() {
        if (head == null) {
            System.out.println("The list is empty");
            return;
        }
        Employee currentEmployee = head;
        while (currentEmployee != null) {
            System.out.println(currentEmployee);
            currentEmployee = currentEmployee.getNext();
        }
    }

    //根据id查找雇员
    public Employee showEmployeeById(int employeeId) {
        Employee employee = head;
        while (employee != null) {
            if (employee.getId() == employeeId) {
                return employee;
            }
            employee = employee.getNext();
        }
        return null;
    }

    public void removeEmployeeById(int employeeId) {
        if (head == null) {
            System.out.println("The list is empty");
            return;
        }

        if (head.getId() == employeeId) {
            head = head.getNext();
            return;
        }

        Employee currentEmployee = head;
        while (currentEmployee.getNext() != null) {
            if (currentEmployee.getNext().getId() == employeeId) {
                currentEmployee.setNext(currentEmployee.getNext().getNext());
                return;
            }
            currentEmployee = currentEmployee.getNext();
        }

        System.out.println("Employee not found.");
    }

    public Employee getHead() {
        return head;
    }

    public void setHead(Employee head) {
        this.head = head;
    }
}
