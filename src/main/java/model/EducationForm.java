package model;


public enum EducationForm {

 FULL_TIME("FULL_TIME"), PART_TIME("PART_TIME");

 private String value;

 EducationForm(String value) {
  this.value=value;
 }

 public String getValue() {
  return value;
 }
}

