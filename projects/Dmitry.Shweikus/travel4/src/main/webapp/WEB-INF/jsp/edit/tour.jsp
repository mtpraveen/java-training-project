<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="/WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<x:page editor="1">
<script>
$(document).ready(function(){
	$("#tourname").rules("add", {
		remote: {
	        url: "<c:url value="/ajax/tour" />",
	        type: "get",
	        data: {
	          name: function() {
	            return $("#tourname").val();
	          },
	          id: function() {
		            return $("#entityid").val();
		      }
	        }
	    },
        messages: {
	        remote: "Tour with this name allready exists"
	    }  
	});
  });
</script>
<form action='<my:Save/>' method="post" class="cmxform" id="commentForm">
	<legend>Tour</legend>
	<input type="hidden" value="${entityid}" name="id" id="entityid"/>
	<input type="hidden" value="tours" name="table"/>
	<my:FieldCaption>Name: *</my:FieldCaption>
	<input type="text" id="tourname" name="name" value="${tour.name}" class="required" minlength="5">
	<my:FieldCaption>Transport:</my:FieldCaption>
	<select name="transportKind">
		<option value="BUS"   <c:if test="${tour.transportKind == 'BUS'}">selected</c:if>>BUS</option>
		<option value="AVIA"  <c:if test="${tour.transportKind == 'AVIA'}">selected</c:if>>AVIA</option>
		<option value="TRAIN" <c:if test="${tour.transportKind == 'TRAIN'}">selected</c:if>>TRAIN</option>
	</select>
	<my:FieldCaption>Kind:</my:FieldCaption>
	<select name="travelKind">
		<option value="REST"      <c:if test="${tour.travelKind == 'REST'}">selected</c:if>>REST</option>
		<option value="SHOPPING"  <c:if test="${tour.travelKind == 'SHOPPING'}">selected</c:if>>SHOPPING</option>
		<option value="TOUR"      <c:if test="${tour.travelKind == 'TOUR'}">selected</c:if>>TOUR</option>
	</select>
	<my:FieldCaption>Days: *</my:FieldCaption>
	<input type="number" name="daysCount" value="${tour.daysCount}" class="required digits" min="1">
	<my:FieldCaption>Description: *</my:FieldCaption>
	<input type="text" name="description" value="${tour.description}" class="required" minlength="3">
	<my:FieldCaption>Documents: *</my:FieldCaption>
	<input type="text" name="requiredDocuments" value="${tour.requiredDocuments}" class="required" minlength="3">
	<br />
	<input type="submit">
</form>
</x:page>
