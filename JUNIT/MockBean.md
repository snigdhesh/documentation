Instead of using Mockito framework : @Mock and @InjectMocks
We can use Spring Boot framework   : @MockBean and @Autowired

@MockBean
- Includes Mockito's @Mock functionality.
- Replaces bean in Spring Application Context, with 'mock bean' if it is already there. 
  - Else adds 'mock bean' into Spring Application Context.

This mocked bean from Spring Application Context, is available for dependency injection via @Autowired.
So use @MockBean when you need to inject regular beans from app context
As @MockBean has @Mock functionality, we can also use @MockBean to inject beans annotated with @Mock also.


#### Without @MockBean : Remember @Mock and @InjectMocks are coming from Mockito framework
    @Mock
    private UserRepository userRepository;    

    @InjectMocks //This will only inject dependencies that are annotated with @Mock, 
                 //but we can't inject regular beans if we want to.
    private UserService userService;


#### With @MockBean : Remember @MockBean and @Autowires are coming from Spring-Boot

    @MockBean //Create mock for userRepository bean.
    private UserRepository userRepository;   
    
    @Autowired //Now this will also add all beans from application context along with mocked userRepository Bean.
    private UserService userService;

Note: Using @MockBean and @Autowired, we can inject any bean - say it might be @Mock annotated bean for beans from app context.