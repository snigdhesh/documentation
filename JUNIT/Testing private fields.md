**Note:** Test private fields only if it's absolutely necessary

Spring framework provides a utility class : `ReflectionTestUtils``

Using this class
- we can get/set non-public fields directly.
- Can also invoke non-public methods.


## Example

#### User.java
        public class User {
            private int id;
        }

        private String getUserDetails(){
            return getId();
        }

I'd like to use private field `id` in my test class, like shown below

        @Autwored
        private User user;

        //Set private field: {param1: target-object , param2: target field, param3: field value}
        ReflectionTestUtils.setField(user, "id",1);
        
        //Get private field : { param1: target-object , param2: target private field }
        ReflectionTestUtils.getField(user, "id");

        //Invoke private method : { param1: target-object , param2: target private method name }
        ReflectionTestUtils.invokeMethod(user, "getUserDetails");

        //Assertion : { param1: expected output, param2: private method call, param3: Error message, if assertion fails}
        assertEquals("1",ReflectionTestUtils.invokeMethod(user, "getUserDetails"), "Fail to call private method")