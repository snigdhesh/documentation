# High level steps: WAF creation


- <details>
  <summary>Networking</summary>

  - <details>
      <summary>Create VPC</summary>

      - tagName: <ANY> Ex: WAF-VPC
      - IPV4-CIDR: 10.0.0.0/16
        
      ```

      | SERVICE | IP range   |
      |---------|------------|
      | VPC     | 10.0.0.0/16|
      | SUBNETS | 10.0.0.0/24|
      | IP      | 10.0.0.0/32|

      ```


  -  <details>
      <summary>Create VPC > Internet gateway</summary>

      - Just add tags
      - Internet gateways > Actions > Attach to VPC
  
  -  <details>
        <summary>Create VPC > Subnets</summary>

        - Create subnet
          - Select VPC_ID
            - Give Subnet name
            - Give Availability zone: Select any one zone
            - IPV4 VPC CIDR Block: 10.0.0.0/16
            - IPV4 subnet CIDR block: 10.0.1.0/24
          
        - Create another subnet
            - Give Subnet name
            - Give Availability zone: Select another zone
            - IPV4 VPC CIDR Block: 10.0.0.0/16
            - IPV4 subnet CIDR block: 10.0.2.0/24

  - <details>
      <summary>Create VPC > Route tables</summary>

      - Give route table name
      - Select VPC name/ID
      - Create route table 
      - Go to "subnet association" > "subnets without explicit association" > edit
      - Check all subnets > save associations
      - Edit routes to attach internet gateway:
        - Click on "edit routes"
        - Click "Add route" > select "0.0.0.0/0"
        - Choose "internet gateway"
        - Save changes

- <details>
    <summary>EC2 instance</summary>

    - Create EC2 instance.
    - Under "Network Settings"
    - Select VPC created in previous step instead of default VPC.
    - Select subnets created in previous step instead of default subnets.
    - Enable public IP.
    - Launch instance.
    - Connect to instance (if git bash doesn't work try aws console to connect)
    - Test static apache webpage via "httpd" (Install > start > enable httpd)



-  <details>
      <summary>Target groups & Load Balancer</summary>

      - Create target group
        - Target group name
        - Chose VPC created in previous step
        - Click "next"
        - Select available instance > click "include as pending below"
        - Create target group.
      - Create load balancer
        - Choose VPC created in previous step
        - Choose subnets created in previous step
        - Create new "security group" > Choose VPC created in previous step
          - Add "All traffic" inbound rule.
          - Create "security group"
        - Select "security group" that you just created
        - Select traget group
        - Create load balancer
        - Test load balancer URL

- <details>
    <summary>Set IP address</summary>

    - Go to "WAF & Shield" service
    - Create IP Set
      - Give IP set name
      - Give description
      - Give region (EC2 instance region)
      - Select IPV4 and copy public IPV4 adress of your machine,followed by /32 `Ex: 192.0.0.1/32`  
        `Note:` (You can search online / Run "ipconfig" in windows to get IPV4 address)
      - Create IP Set

- <details>
    <summary>WAF</summary>

    - Go to "WAF & Shield" service > "Web ACLs"
    - ACL: Access Control List
    - Set region (Region where EC2 instance is running)
    - Add "AWS resources" > "Application Load Balancer" > choose load balancer
    - Click next
    - Add rules > "add my own rules" > "Rule Type" > "IP Set"
    - Give rule name
    - Choose IP set
    - Choose actions > "allow"
    - Click "Add Rule" 
    - Select rule we just created
    - Click next until you find "Create Web ACL" and click it.




