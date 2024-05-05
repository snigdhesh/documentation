## AWS:Key Management Service

- <details>
    <summary>Types of encryptions</summary>

  - <details>
        <summary>In transit encryption</summary>

        While transferring data, we need to buy SSL certificate for authentication.
        ex; google runs on https protocol, meaning port 443

        But if we have a website - by default 
        - it's unsecured
        - It runs on http, meaning port 80

        To secure this, we need to buy SSL certificate - from domains like goDaddy or Hostinger
        then our website becomes secured, 
        then protocol changes to https, and port 443
    </details>

  - <details>
        <summary>Server side encryption</summary>

        If we upload files to S3 bucket, we can encrypt/decrypt data within the S3 Bucket(S3 bucket is the Server)

    </details>

  - <details>
        <summary>client side encryption</summary>

        We use some tools to manually encrypt/decrypt data.
        Then we upload to S3 bucket (server) 

    </details>
  </details>


`Note:` AWS provides default keys. We can create our own keys under `KMS > customer managed keys`

#### Create S3 Bucket
----------------------------------
- Create S3 bucket
- Give Bucket Name
- Object Ownership: 
   - ACLs enabled  
   - Bucket owner preferred
- Unblock public access
- Enable bucket versioning
- **Encryption type:** Server side:SSE-S3 (cause we are dealing with S3 bucket, S3Bucket is the server)
- click **create bucket**
- Open bucket
- Update file `Ex: index.html` and click upload
- go to bucket > `properties` tab > Here you can see `Default encryption`

### Create IAM User
--------------------
- Go to IAM dashboard
- Create user. `Example: John`
- Provide access to console > I want to create an IAM user > custom password
- Add policy "AmazonS3FullAccess"
**Note:** Policy is an object in AWS, that defines permissions.
**Note:** Set of grouped permissions is called policy. Ex: AmplifyAdminPolicy has createService, readService, deleteService permissions.


#### Create Customer managed keys
-------------------------------------
- Go to KMS service
- Select `customer managed keys` > create key
- **keyType:** Symmetric (S3 can access only Symmetric)
- **keyUsage:** Encrypt and Decrypt.
- Leave `Advanced Options` as it is and click `Next`
- Add lables
   - Give alias name. `Ex: KMS_alias` and click `Next`
- Define key admin premissions
   - Select `John` (**John** is an IAM user, given access only to S3 Bucket)
   - Click `next`
- Define key usage permissions
  - Select `John` and click next > Finish


#### Modifiy encryption type on S3 Bucket
-------------------------------------------
Go to S3 bucket > properties tab > Default encryption > edit 
  - **Encryption Type:** choose "Server-side-encryption with AWS KMS"
  - **AWS KMS key:** Choose your KMS key ARN (Ex: Your can see alias name "KMS_alias" under keyId)
  - **BucketKey:** enable
  - Save changes

**Note:** 
- At this point you can see modified **default encryption** reflected. Go to S3 Bucket > properties tab > Check default encryption > Click on key, to see your alias name.
- Upload a new file > Go to **properties** > check **default encryption** (You can see default encryption is updated here too)

#### Testing
----------------
- Click > profile pic > copy `accountId`
- Go to incognito window > login to amazon console with new accountId as IAM user.
    - username: John
    - password: 


- Once you login you can see this user has only view access to `S3 bucket.`

**Note:** You can open html page from S3 bucket `Open` button.





#### Questions
----------------
**What are AWS keys?**  

**What if we have user only in "Key admin", but not in "Key user" and vice-versa?**  
Answers unclear, but it works either way.  

**How to delete keys?**  
KMS > Actions > Schedule key deletion

**How to restrict user to access S3 bucket?**  
- If you want to restrict user named `John` to access files in S3, you need to do following.
- Remove user named `John` from `KMS > customer managed keys > Key admin & Key users`

**What's difference between "Key admin permissions" and "Key user permissions"?
**Key admin permissions** will be assigned to a user. 
    - This user can manage (perform CRUD operations) key, 
    - but cannot use this key, to perform encryption/decryption
**Key user permissions** will be assigned to a user. 
    - This user can use this key to perform encryption/decryption, 
    - but cannot manage (perform curd operations) this key.

