# University Halls of Residence Management System

## **Software Design Requirements**

### **Functional Requirements**

1. The application shall allow users to create, store, and manage records for individuals living in university halls of residence, including both students and employees.
2. The application shall support inheritance-based class hierarchy with a base `Person` class, extended by `Student` and `Employee` classes.
3. The application shall include a `Hall` class to represent residential halls.
4. The application shall include a `Payment` class to handle payment-related information (e.g., rent status or discounts for responsible roles).
5. The application shall include a `Store` class responsible for maintaining an in-memory collection (list) of all `Person` objects (students and employees).
6. The application shall provide a fully programmatic GUI (no visual designer/tool usage allowed) using coded components only.
7. The application shall support entry of new records via an on-screen form that captures all fields inherited from `Person` plus specific fields for `Student` or `Employee` as appropriate.
8. The application shall automatically or manually assign a date stamp (e.g., registration or move-in date) when a new record is created, using a `Date` type field in the `Person` class.
9. The application shall allow sequential browsing of stored records: each activation of "Next" displays the next record, and upon reaching the end, it wraps around to the first record.
10. The application shall support persistence by saving all records from the `Store` to a plain text file.
11. The application shall support loading records from a plain text file into the `Store`, overwriting or appending as appropriate.
12. The application shall support special needs attributes for students (e.g., requirement for ground-floor room, dietary requirements such as vegetarian/vegan).
13. The application shall provide a mechanism to assign students to appropriate halls based on their special needs (e.g., dietary preferences or accessibility requirements).
14. The application shall differentiate between regular paying students, employees, and senior/responsible students who receive reduced or free rent.

### **Non-Functional Requirements**

1. The GUI must be created entirely through code (no drag-and-drop or visual editor usage).
2. Data persistence must use plain text files (no database or binary serialization required).
3. The system shall handle wrapping navigation through records seamlessly.
4. The application shall be structured using object-oriented principles (inheritance, encapsulation, and composition).
5. The design shall be extensible to allow future addition of new features (e.g., more assignment rules).

## **User Stories**

### **Core Data Entry and Management**

1. **As a hall administrator**, I want to enter a new student record using an on-screen form so that I can capture personal details, student-specific information (e.g., special needs, diet), and registration date.
2. **As a hall administrator**, I want to enter a new employee record using an on-screen form so that I can capture personal details and employment-related information.
3. **As a hall administrator**, I want the system to automatically record a date stamp when I save a new person record so that I have an audit trail of when the resident was added.
4. **As a hall administrator**, I want to browse through all resident records sequentially (with wrap-around) so that I can review existing data without searching.

### **Data Persistence**

5. **As a hall administrator**, I want to save all current resident records to a text file so that data persists between application sessions.
6. **As a hall administrator**, I want to load resident records from a previously saved text file so that I can restore or transfer data.

### **Hall and Assignment Features**

7. **As a hall administrator**, I want to define multiple halls of residence so that residents can be associated with specific buildings.
8. **As a hall administrator**, I want the system to suggest or automatically assign a student to a suitable hall based on their dietary needs (e.g., vegetarian/vegan) so that catering and accommodation match requirements.
9. **As a hall administrator**, I want to mark certain students as having accessibility needs (e.g., ground-floor room required) so that assignments respect those constraints.
10. **As a hall administrator**, I want to record payment status or rent reductions for senior/responsible students and employees so that financial arrangements are tracked.

### **GUI and Usability**

11. **As a hall administrator**, I want a clean, programmatically built graphical interface with clear buttons and forms so that I can perform all operations without command-line input.
12. **As a hall administrator**, I want visible feedback when saving or loading data so that I know the operation succeeded or failed.

### **Extensibility (Additional Features)**

13. **As a hall administrator**, I want additional intelligent assignment rules (beyond diet) so that students are placed in the most appropriate hall according to multiple criteria.
14. **As a developer/maintainer**, I want the class structure to be modular so that new person types or features can be added with minimal changes to existing code.

---

**Project Parts Covered**

- **Part 1**: Basic programmatic GUI  
- **Part 2**: Class design (`Person`, `Student`, `Employee`, `Hall`, `Payment`, `Store`)  
- **Part 3**: Save/load to/from text file  
- **Part 4**: Enhanced features (e.g., needs-based hall assignment)