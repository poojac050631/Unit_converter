<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    
</head>
<body>

<h1>Software Requirement Specification (SRS)</h1>
<h2>For Unit Converter (Length / Weight / Temperature)</h2>

<p><strong>Ajeenkya D.Y. Patil University, School of Engineering, Pune</strong></p>
<p><strong>Date:</strong> 08 Sep 2024</p>
<p><strong>Prepared by:</strong> Pooja Choudhary (2024-B-05062006)</p>

<hr>

<h2>Index</h2>

<pre>
1. Introduction
   1.1 Purpose
   1.2 Scope
   1.3 Definitions, Acronyms, and Abbreviations
   1.4 References
   1.5 Overview

2. Overall Description
   2.1 Product Perspective
   2.2 Product Functions
   2.3 User Classes and Characteristics
   2.4 Operating Environment
   2.5 Constraints
   2.6 Assumptions and Dependencies

3. System Features
   3.1 Length Conversion
   3.2 Weight Conversion
   3.3 Temperature Conversion

4. Functional Requirements

5. Non-Functional Requirements

6. External Interface Requirements
   6.1 User Interface
   6.2 Hardware Interface
   6.3 Software Interface

7. System Design (with Web Diagram)
   7.1 System Architecture Diagram
   7.2 Data Flow Diagram (DFD) – Level 0 and Level 1

8. Use Case Scenarios
   8.1 Use Case 1 – Length Conversion
   8.2 Use Case 2 – Weight Conversion
   8.3 Use Case 3 – Temperature Conversion

9. Future Enhancements

10. Appendix
   10.1 Conversion Examples
   10.2 Abbreviations
</pre>

<hr>

<h2>1. Introduction</h2>

<h3>1.1 Purpose</h3>
<p>This document specifies the requirements for the Unit Converter application, which enables conversion of values across Length, Weight, and Temperature units. The goal is to provide accurate, fast, and user-friendly conversions for students, professionals, and everyday users.</p>

<h3>1.2 Scope</h3>
<ul>
    <li>Supports conversion of Length, Weight, Temperature</li>
    <li>Works offline (standalone app)</li>
    <li>Provides quick results with accuracy</li>
    <li>Can run on desktop, web, and mobile platforms</li>
</ul>

<h3>1.3 Definitions, Acronyms, Abbreviations</h3>
<ul>
    <li><strong>SRS:</strong> Software Requirement Specification</li>
    <li><strong>UI:</strong> User Interface</li>
    <li><strong>FR:</strong> Functional Requirement</li>
    <li><strong>NFR:</strong> Non-Functional Requirement</li>
</ul>

<h3>1.4 References</h3>
<ul>
    <li>IEEE SRS Standards (830-1998)</li>
    <li>SI Units and Imperial System References</li>
</ul>

<h3>1.5 Overview</h3>
<p>This SRS describes the complete requirements, design considerations, and diagrams for the Unit Converter system.</p>

<hr>

<h2>2. Overall Description</h2>

<h3>2.1 Product Perspective</h3>
<p>The application is a standalone tool with simple UI components: input box, dropdowns, and output display.</p>

<h3>2.2 Product Functions</h3>
<ul>
    <li>Select category (Length/Weight/Temperature)</li>
    <li>Input numeric values</li>
    <li>Select source & target units</li>
    <li>Display result instantly</li>
</ul>

<h3>2.3 User Classes and Characteristics</h3>
<ul>
    <li>Students</li>
    <li>Professionals</li>
    <li>General Users</li>
</ul>

<h3>2.4 Operating Environment</h3>
<ul>
    <li>Web Browser</li>
    <li>Android/iOS App</li>
    <li>Desktop App</li>
</ul>

<h3>2.5 Constraints</h3>
<ul>
    <li>Limited to 3 categories (v1.0)</li>
    <li>Accuracy up to 4 decimals</li>
</ul>

<h3>2.6 Assumptions and Dependencies</h3>
<ul>
    <li>User enters numeric values</li>
    <li>Universal conversion formulas are used</li>
</ul>

<hr>

<h2>3. System Features</h2>

<h3>3.1 Length Conversion</h3>
<p>Units: Meter, Kilometer, Centimeter, Inch, Foot, Yard, Mile</p>

<h3>3.2 Weight Conversion</h3>
<p>Units: Kilogram, Gram, Pound, Ounce, Tonne</p>

<h3>3.3 Temperature Conversion</h3>
<p>Units: Celsius, Fahrenheit, Kelvin</p>

<hr>

<h2>4. Functional Requirements</h2>
<ul>
    <li>FR1: Select category</li>
    <li>FR2: Enter numeric value</li>
    <li>FR3: Select source and target units</li>
    <li>FR4: Display converted result</li>
    <li>FR5: Handle invalid inputs</li>
    <li>FR6: Reset/Clear option</li>
</ul>

<hr>

<h2>5. Non-Functional Requirements</h2>
<ul>
    <li>Performance: Convert in less than 1 second</li>
    <li>Usability: Simple and intuitive UI</li>
    <li>Portability: Works on web, mobile, desktop</li>
    <li>Scalability: Easy future extensions</li>
</ul>

<hr>

<h2>6. External Interface Requirements</h2>

<h3>6.1 User Interface</h3>
<ul>
    <li>Input text box</li>
    <li>Unit dropdown</li>
    <li>Convert button</li>
    <li>Reset button</li>
</ul>

<h3>6.2 Hardware Interface</h3>
<p>Keyboard, mouse, touchscreen</p>

<h3>6.3 Software Interface</h3>
<p>No external database required</p>

<hr>

<h2>7. System Design</h2>

<h3>7.1 System Architecture Diagram</h3>

<pre>
 +-------------------+
 |       User        |
 +---------+---------+
           |
           v
 +----------+----------+
 |   User Interface    |
 |  (Input / Output)   |
 +----------+----------+
           |
           v
 +----------+----------+
 | Conversion Engine   |
 | (Length/Weight/Temp)|
 +----------+----------+
           |
           v
 +---------+---------+
 |   Result Display   |
 +-------------------+
</pre>

<h3>7.2 Data Flow Diagram (DFD)</h3>

<p><strong>Level 0:</strong></p>
<pre>
 User → Unit Converter App → Output
</pre>

<p><strong>Level 1:</strong></p>
<pre>
User
 ├──> Select Category
 ├──> Enter Input
 ├──> Conversion Engine
 └──> Display Result
</pre>

<hr>

<h2>8. Use Case Scenarios</h2>

<h3>8.1 Length Conversion</h3>
<p>Input: 5000 → Output: 5 km</p>

<h3>8.2 Weight Conversion</h3>
<p>Input: 2 kg → Output: 4.409 lbs</p>

<h3>8.3 Temperature Conversion</h3>
<p>Input: 100°C → Output: 212°F</p>

<hr>

<h2>9. Future Enhancements</h2>
<ul>
    <li>Add Volume, Area, Speed, Currency conversions</li>
    <li>Voice input</li>
    <li>Conversion history</li>
</ul>

<hr>

<h2>10. Appendix</h2>

<h3>10.1 Conversion Examples</h3>
<ul>
    <li>1 inch = 2.54 cm</li>
    <li>1 mile = 1.609 km</li>
    <li>1 kg = 2.204 lb</li>
    <li>0°C = 32°F = 273.15 K</li>
</ul>

<h3>10.2 Abbreviations</h3>
<ul>
    <li>m: meter</li>
    <li>km: kilometer</li>
    <li>kg: kilogram</li>
    <li>lb: pound</li>
    <li>°C: Celsius</li>
    <li>°F: Fahrenheit</li>
    <li>K: Kelvin</li>
</ul>

</body>
</html>
