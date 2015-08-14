HugeInteger is a Service which uses a 40-element array of digits to store integers as large as 40 digits each and provides methods such as parse, toString, add and subtract. For comparing HugeInteger objects, the following methods can be used: isEqualTo, isNotEqualTo, isGreaterThan, isLessThan, isGreaterThanOrEqualTo and isLessThanOrEqualTo. Each of these is a predicate method that returns true if the relationship holds between the two HugeInteger objects and returns false if the relationship does not hold. 

Parameter  Definitions: 
=============================
**integer** - The HugeInteger input <br/>
**operation** - The operation to be performed. Currently supports isZero, isEqualTo, isNotEqualTo, isGreaterThan, isLessThan, isGreaterOrEqualTo, isLessThanOrEqualTo <br/>
**compare** - The HugeInteger to be compared with (optional for isZero operation) <br/>
**addTo** - The HugeInteger to be added with <br/>
**subFrom** - The HugeInteger to be subtracted from <br/>

URIs:
==============================
**/HugeInteger** - to perform operations mentioned in Parameter Definitions -> operation <br/>
**/add** - to add to another HugeInteger <br/>
**/sub** - to subtract from another HugeInteger <br/>
**/toString** - returns the stored HugeInteger <br/>

Note: /add & /sub modify the original HugeInteger. Use Carefully! <br/>

Usages:
==============================

Sample Input: **http://localhost:8080/HugeInteger?integer=123&operation=isZero**  Output: **false** <br/>
Sample Input: **http://localhost:8080/HugeInteger?integer=123&operation=isGreaterThan&compare=231**  Output:**false** <br/>
Sample Input: **http://localhost:8080/add?integer=123&addTo=11** Output:**134** <br/>
Sample Input: **http://localhost:8080/toString** Output:**144** <br/>

