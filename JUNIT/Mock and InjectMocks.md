**Note:** mockito and jupiter frameworks comes with spring-boot-starter-test depedency


## UserService.java

    @Autowired
    private UserRepository userRepository;

    private void getUsers(){
        userRepository.findAll();
    }


## Testing UserService.java

**step1:** Create mock for userRepository bean // Just use @Mock annotation to create mock for the bean

    @Mock
    private UserRepository userRepository;    

**step2:** Inject mock into the UserService.java //Just use @InjectMocks annotation to inject mocks we just create - into userservice.java

    @InjectMocks //This will only inject dependencies that are annotated with @Mock, 
                 //but we can't inject other beans, which are in application context. 
                 //(If at all we want to use those beans in this code)
    private UserService userService;

**step3:** Return custom response from repository

    when(userRepository.findAll()).thenReturn(['user1','user2','user3']);

**step4:** Assert things

    assertEquals(['user1','user2','user3'], userService.getUser())

**step5:** Verify if repository method is actually called or not.

    verify(userRepository).findAll(); //To check if method is called 1 time.
    verify(userRepository, times(3)).findAll(); //To check if method is called 3 times.