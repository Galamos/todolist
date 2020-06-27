<%--
  Created by IntelliJ IDEA.
  User: SP3
  Date: 27/06/2020
  Time: 04:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>To-Do List</title>
    <link href="/css/style.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
    <script type="text/javascript" src="/script/script.js"></script>
  </head>
  <body ng-app="taskManager" ng-controller="itemContoller">
  <div class="container">

    <div class="app-title">
      <h1>To-Do List</h1>
    </div>
    <div class="item_list_content">
      <div class="individual_item" ng-repeat="item in list" ng-init="statusCls=getClass(item)">
        <div class="item">
          <div class="item_value"><p>{{ item.description }}</p></div>
          <a class="item_edit" ng-click="editItem(item)">edit</a>
          <a ng-click="deleteItem(item)" class="delete_button"></a>
          <input type="checkbox" ng-checked="">
        </div>
      </div>
    </div>


    <form ng-submit="addItem()">
      <div class="row">
        <input required autocomplete="off" maxlength="100" class="form-control col-xs-12" type='text' placeholder="Task Name" ng-model="itemForm.itemDescription">
        <button type="submit" class="btn btn-default btn-lg btn-add-custom col-xs-12">
          <i class="fa fa-plus-square" aria-hidden="true"></i> Add new
        </button>

      </div>
    </form>

  </div>
  $END$
  </body>
</html>
