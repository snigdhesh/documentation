- MockMVC Component is provided by Spring.
- We use this component to test controller classes.
- This enables us to make requests and responses without running a server.


1. Add @AutoConfigureMockMvc
2. Inject MockMvc
3. Perform web request
4. Define expectations
5. Assert results

        @AutoConfigureMockMvc
        @SpringBootTest
        public class ControllerTest{
            
            @Autowired
            prviate MockMvc mockMvc;

            @Test
            public void getStudents(){
                MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/"))
                                            .andExpect(status().isOk()).andReturn();

                //Do assertions with mvcResult

                //Note: Remember it's mockMVC, meaning it can also be used to test views. Whether controller is returning an expected view or not.
            }
        }