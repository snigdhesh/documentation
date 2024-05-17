Note: Assuming ASG is setup at this point.

Predictive scaling : Scale based on current traffic/metrics
This is purely based on assumption, so it may not be right fit for all the scenarios. Hence consider 'Dynamic Scaling'
Ex: ASG will keep track of how existing instances are running, and predicts when to spin up/down more instances.

Dynamic scaling : 
- Target Tracking Scaling: Automatically spin instances up/down based on load or other metrics we setup.
  -  Ex: If one server is occupied 50, another instance can be spinned up.
- Step scaling: Scales instances in predefined increments based on CloudWatch alarms. 
  - Cloud watch alarms : Services provided by AWS to monitor and respond based on changes in your AWS environment.
  - Here scaling is done at different levels (a.k.a steps)
  - We can spin 2 more instances, when current CPU utilization is between 50 - 70 (This is kind of one step)
  - We can sping 4 more instances, when CPU utilization crosses 70 (This is kind of another step)
  - So we can configure scaling at different levels (a.k.a steps). Hence came the name 'step-scaling'



Create step scaling policy
Login to asg-instance
Install epel : sudo amazon-linux-extras install epel -y //I think this is to increase traffic
Install stess: sudo yum install stress -y

vim asgload.sh //I think this is a shellscript to increase load

#Define the maximum CPU laod percentage
max_load=90

#Define the step size to increase the load
step_size=30

#Define the duration to maintain the load at 90% (in seconds)
duration=1800 % 30 minutes

#Loop to gradually increase the CPU load
for load in ${seq}