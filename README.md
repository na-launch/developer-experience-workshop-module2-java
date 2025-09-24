# 🚀 **Module 2: Introduction to DevSpaces**

**Technology Stack:**  

- Quarkus  
- DevSpaces  

---

## 🎯 **Scenario**

Inside your workspace is a very simple Quarkus Application. The application returns a “hello world” based on the language you choose for it.

The country is based on a 2-letter country code.  
For example, `countrycode=IT` will output “ciao mondo” (IT is for Italy).  

You can find all the mappings in the `translate.json` file, located in your `resources` folder.  
There is also a timestamp after the “hello world” message.

---

## 🧩 **Challenge**

- [ ] Format the hello timestamp to use **Eastern Standard Time** (New York time)  
- [ ] Format the hello timestamp to display as **`{HH:MM MM/DD/YYYY}`**  
- [ ] Write a test case that verifies both the timezone and the new date format  
- [ ] Append a query string to take an argument for the country code (e.g., `/api/query?countryCode=en`) and set the API to return the correct greeting based on the country used in the query string  
- [ ] Add an `/api/magic8` endpoint that returns a random country code (like a magic 8-ball) and returns a random hello world greeting  
- [ ] Write additional tests for `/api/query` and `/api/magic8`  
- [ ] Add an additional endpoint for `/api/query` which functions the same but uses **POST** instead; use `curl` to test

---

## ✅ **Key Takeaways**

- 👀 First look at Dev Spaces  
- ⚙️ Set up a Quarkus App in Dev Spaces  
- 🔄 Ran Quarkus in Developer Mode with Live Reload!
