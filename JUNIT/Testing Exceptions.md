Test whether our code is handling exception or not incase of bad executions.

**step1:** Mock throwing an exception

    when(userRepository.findAll()).thenThrow(new RuntimeException());

**step2:** Check if exception is thrown, when method is called

    assertThrows(RuntimeException.class, () -> { userService.getUsers()})
    //consider userService.getUsers() calls userRepository.findAll() internally



**step1:** Mock throwing an exception

    when(userRepository.findAll())
                    .thenThrow(new RuntimeException()) //Throw exception when userRepository.findAll() is called first time.
                    .thenReturn(['user1']) //But return a response when userRepository.findAll() is called second time.

**step2:** Check if exception is thrown, when method is called

    assertThrows(RuntimeException.class, () -> { userService.getUsers()}) //You get exception on first call.
    assertEquals(['user1'], userService.getUsers()); //No exception, but you get response on second call.