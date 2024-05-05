# High level steps: WAF creation

        
    

  | SERVICE | IP range   |
  |---------|------------|
  | VPC     | 10.0.0.0/16|
  | SUBNETS | 10.0.0.0/24|
  | IP      | 10.0.0.0/32|

    

- <details>
  <summary>Networking</summary>

  - <details>
      <summary>Create VPC</summary>

      - Select `VPC only`  
      - **tagName:** anyName `Ex: WAF-VPC`  
      - **IPV4-CIDR:** 10.0.0.0/16  


  -  <details>
      <summary>Create VPC > Internet gateway</summary>

      - Just add tags
      - Internet gateways > Actions > Attach to VPC
  
  -  <details>
        <summary>Create VPC > Subnets</summary>

        - Create subnet
          - Select "VPC ID"
          - Under "Subnet Settings"
            - Give Subnet name
            - Give Availability zone: Select any one zone
            - **IPV4 VPC CIDR Block:** 10.0.0.0/16
            - **IPV4 subnet CIDR block:** 10.0.1.0/24
          
        - Create another subnet
            - Give Subnet name
            - Give Availability zone: Select another zone
            - **IPV4 VPC CIDR Block:** 10.0.0.0/16
            - **IPV4 subnet CIDR block:** 10.0.2.0/24

  - <details>
      <summary>Create VPC > Route tables</summary>

      - Give route table name
      - Select VPC name/ID
      - Create route table 
      - Go to **subnet association** > **subnets without explicit association** > edit
      - Check all subnets > save associations
      - Edit routes to attach internet gateway:
        - Click on `edit routes`
        - Click `Add route` > select "0.0.0.0/0"
        - Choose `internet gateway`
        - Save changes

- <details>
    <summary>EC2 instance</summary>

    - Create EC2 instance. **(With LINUX 2 AMI)**
    - Under `Network Settings`
    - Select VPC created in previous step instead of default VPC.
    - Check **SSH, HTTPS, HTTP**
    - Select any subnet created in previous step instead of default subnet.
    - Enable `Auto assign public IP`
    - Launch instance.
    - Connect to instance (if git bash doesn't work try aws console to connect)
    - Test static apache webpage via "httpd" (Install > start > enable httpd)



-  <details>
      <summary>Target groups & Load Balancer</summary>

      - Create target group
        - Target group name
        - Chose VPC created in previous step
        - Click `next`
        - Select available instance > click "include as pending below"
        - Create target group.
      - Create load balancer
        - Choose VPC created in previous step
        - Choose all regions under **Mappings**
        - Choose subnets created in previous step
        - Create new **security group** > Choose VPC created in previous step
          - Add **All traffic** inbound rule.
          - Create **security group**
        - Select **security group** that you just created
        - Select **target group**
        - Create **load balancer**
        - Test load balancer URL

- <details>
    <summary>Set IP address</summary>

    - Go to `WAF & Shield` service > **IP Sets**
    - Create IP Set
      - Give IP set name
      - Give description
      - Give region (EC2 instance region)
      - Select IPV4 and paste `public IPV4 adress` of your laptop,followed by /32 `Ex: 192.0.0.1/32`  
        `Note:` You can search online [whatismyipaddress.com](https://whatismyipaddress.com/)
      - Create IP Set
      - Select appropriate region, to see created `IP Set`

- <details>
    <summary>WAF</summary>

    - Go to **WAF & Shield** service > **Web ACLs**
    - **Note:** ACL = Access Control List
    - Choose region (Region where EC2 instance is running)
    - Give name
    - Add **AWS resources** > **Application Load Balancer** > choose load balancer
    - Click `next`
    - Add rules > **add my own rules** > **Rule Type** > **IP Set**
    - Give rule name
    - Choose IP set
    - Choose **Action** > **Allow**
    - Click **Add Rule** 
    - Select rule we just created
    - Set rule priority > Select rule
    - Click next until you find **Create Web ACL** and click it.

## Testing
- Hit load balancer URL, to see if application is still up and running.
- This ensures, our WAF with custom VPC is working.

