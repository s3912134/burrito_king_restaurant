Program Package Structure
The project will be organized into several packages to structure the codebase following the MVC pattern and associated functionalities. The packages will include:
- `model`: Houses all the data-related classes and business logic.
- `view`: Contains all the JavaFX user interface components.
- `controller`: Includes the controllers that manage interactions between the model and the view.
- `util`: Comprises utility classes, such as those for database connections and validation.
- `main`: Contains the primary entry point of the application.
Essential Classes and Their Purpose
`burrito` Package
1. **User**
- Purpose: Represents a user of the application.
- Attributes:
- `String username`
- `String password`
- `String firstName`
- `String lastName`
- `boolean isVIP`
- `String email`
- `List<Order> orders`
- `ShoppingBasket basket`
- Methods:
- Constructor
- Getters and setters
- `addOrder(Order order)`
- `editProfile(String firstName, String lastName, String password)`
- `upgradeToVIP(String email)`
2. **Order**
- Purpose: Represents an order placed by a user.
- Attributes:
- `int orderNumber`
- `List<FoodItem> foodItems`
- `double totalPrice`
- `String status`
- `String placedTime`
- `String collectedTime`
- Methods:
- Constructor
- Getters and setters
- `addFoodItem(FoodItem item)`
- `calculateTotalPrice()`
3. **FoodItem**
- Purpose: Represents a food item that can be added to an order.
- Attributes:
- `String name`
- `double price`
- `int quantity`
- Methods:
- Constructor
- Getters and setters
- `equals()`
- `hashCode()`
4. **ShoppingBasket**
- Purpose: Represents a shopping basket for a user to add food items before placing an order.
- Attributes:
- `List<FoodItem> items`
- Methods:
- Constructor
- Getters and setters
- `addItem(FoodItem item)`
- `removeItem(FoodItem item)`
- `updateItemQuantity(FoodItem item, int quantity)`
5. **Main**
- Purpose: The main entry point of the application.
- Methods:
- `main(String[] args)`
`view` Package
1. **LoginView**
- Purpose: The UI for user login.
- Methods:
- Render login form
- Show error messages
2. **DashboardView**
- Purpose: The UI for the user dashboard.
- Methods:
- Display user information
- Display active orders
- Provide navigation options
3. **OrderView**
- Purpose: The UI for placing and viewing orders.
- Methods:
- Render order form
- Display order details
- Handle user interactions for placing orders
`controller` Package
1. **UserController**
- Purpose: Handles user-related actions and interactions.
- Methods:
- `login(String username, String password)`
- `register(String username, String password, String firstName, String lastName)`
- `editProfile(String firstName, String lastName, String password)`
- `upgradeToVIP(String email)`
2. **OrderController**
- Purpose: Handles order-related actions and interactions.
- Methods:
- `placeOrder(User user, List<FoodItem> items, String placedTime)`
- `collectOrder(User user, int orderNumber, String collectedTime)`
- `cancelOrder(User user, int orderNumber)`
- `exportOrders(User user, List<Order> ordersToExport, String fileName)`
`util` Package
1. **DatabaseUtil**
- Purpose: Utility class for handling database connections and operations.
- Methods:
- `connect()`
- `disconnect()`
- `saveUser(User user)`
- `loadUser(String username)`
2. **ValidationUtil**
- Purpose: Utility class for performing various validations.
- Methods:
- `isValidCreditCard(String cardNumber)`
- `isFutureDate(String expiryDate)`
- `isValidCVV(String cvv)`
Class Relationships
- **User has a ShoppingBasket**: Composition relationship, as the `ShoppingBasket` exists as part of the `User` and cannot exist independently.
- **User has Orders**: Association relationship, where a `User` can have multiple `Order` objects.
- **Order has FoodItems**: Aggregation relationship, where `Order` can contain multiple `FoodItem` objects, but `FoodItem` can exist independently.
- **Controllers interact with Models**: Association relationship, where controllers manage the data in model classes and update the views accordingly.
