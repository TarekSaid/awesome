[#ftl]
[#assign modelList = view.name + "s"]
[#assign bean = view.name + "Bean"]
[#assign capModel = view.name?cap_first]
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
  xmlns:h="http://java.sun.com/jsf/html"
  xmlns:f="http://java.sun.com/jsf/core"
  xmlns:p="http://primefaces.org/ui">
<h:head>
  <title>${capModel}s</title>
</h:head>
<h:body>
  <div align="center">
[#include "crud/mainForm.ftl"]
  </div>
[#include "crud/addForm.ftl"]
[#include "crud/editForm.ftl"]
[#include "crud/deleteForm.ftl"]
</h:body>
</html>