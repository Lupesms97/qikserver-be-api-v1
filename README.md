### Question 1
- **How long did you spend on the test? What would you add if you had more time?**
    - I spent approximately 4 days on the test. If I had more time, I would implement tests and make the code more readable.

### Question 2
- **What was the most useful feature that was added to the latest version of your chosen language? Please include a snippet of code that shows how you've used it.**
    - The most useful feature added in the version 16, i'm currently using is v17 is the record, because of it we have to write less boilerplate code Here's how I've used it:
      ```
      public record OrderResponseDto (
      PromotionDto promotionApplied,
      ProductsDto product,
      int quantity,
      int totalWithDiscount
        
        ){}
        ```

### Question 3
- **What did you find most difficult?**
    - The most difficult aspect of the test was working on inheritance because we normally don't see it.

### Question 4
- **What mechanism did you put in place to track down issues in production on this code? If you didn’t put anything, write down what you could do.**
    - I haven't implemented an in-depth mechanism to track down issues, but I have captured untreated exceptions. I could implement more specific exceptions and register them, and additionally, I could implement detailed logging and use some tools to monitor my app."
### Question 5
- **The Wiremock represents one source of information. We should be prepared to integrate with more sources. List the steps that we would need to take to add more sources of items with different formats and promotions.**
    - To add more sources of items with different formats and promotions, we would need to take the following steps:
        1. Create a new class that implements the PromotionDetails interface with the new fields.
        2. Add the new fields into Response Dto.
        3. Create the interface for the repository and implement it in the PromotionDetailsRepositoryInstance class.
        4. In the service layer, add the new class to the switch case of the getTotalWithDiscountTypeBased method.
        5. In the createResponse method, specify the response for the new method.
           ..
### Recent Judgment Call in Business

## Situation:
Within the past 12 months, I encountered a situation where our company had to decide whether to continue paying for third-party tools or build our own tool for our human resource department.

## Alternatives Considered:
1. **Keep Paying for Third-Party Tools:** We could continue paying for the tools we currently use. However, this would mean an increase in expenses over time as the company grows, and we would have limited control over our data.
2. **Build Our Own Tool:** Alternatively, we could focus on building our own tools in-house. This would require investing in developers and allocating a budget for development, but it could give us more control over our tools and potentially lead to the creation of a tailored solution for our specific needs.

## Decision-Making Process:
1. **Needs Assessment:** We conducted a thorough assessment of our human resource department's needs, identifying pain points and areas for improvement.
2. **Cost-Benefit Analysis:** We analyzed the costs associated with both options, including the initial investment required to build our own tool versus the ongoing expenses of paying for third-party tools.
3. **Resource Allocation:** We evaluated our internal resources, including the availability of skilled developers and the potential impact on other projects.
4. **Risk Evaluation:** We assessed the risks associated with each option, considering factors such as potential development delays, compatibility issues, and long-term scalability.

## Decision:
After careful consideration, we decided to invest in building our own tool for the human resource department. While this option required a significant upfront investment in development resources and time, we believed that having a custom solution tailored to our specific needs would provide greater flexibility and control in the long run. Additionally, building our own tool aligned with our company's strategic goals of fostering innovation and optimizing internal processes.

## Most Innovative Endeavor

### Context:
One of the most innovative endeavors I undertook was the development of a new site interface for our main company brand.

### Problem Addressed:
The problem we aimed to address was the lack of personalized and engaging experiences for our customers on our website. Many users found it challenging to navigate through our vast course catalog and struggled to discover courses that matched their preferences and interests.

### Outcomes:
1. **Interactive Elements:** Introduced features such as course quizzes and virtual previews to enhance user engagement.
2. **Increased Engagement:** Resulted in a significant rise in user interaction and time spent on the platform.
3. **Improved Conversion Rates:** Contributed to higher course enrollment rates due to personalized experiences.

### Importance and Impact:
Developing an innovative site interface was crucial for our company's success in the online learning industry. By addressing the problem of impersonal and uninspiring website experiences, we were able to enhance user engagement, increase course enrollment rates, and ultimately drive business growth. Additionally, the innovative features helped strengthen our brand reputation and position us as a leader in personalized online learning experiences.
=======


### Question 2
- **What was the most useful feature that was added to the latest version of your chosen language? Please include a snippet of code that shows how you've used it.**


### Question 3
- **What did you find most difficult?**


### Question 4
- **What mechanism did you put in place to track down issues in production on this code? If you didn’t put anything, write down what you could do.**


### Question 5
- **The Wiremock represents one source of information. We should be prepared to integrate with more sources. List the steps that we would need to take to add more sources of items with different formats and promotions.**


