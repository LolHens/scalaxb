/**
 * @author  e.e d3si9n
 */

import scalaxb._
import Scalaxb._
import general._
import DefaultXMLProtocol._

object GeneralUsage {
  def main(args: Array[String]) = {
    allTests
  }

  def allTests = {
    testSingularSimpleType
    testList
    true
  }
  
  def testSingularSimpleType {
    val subject = <foo xmlns="http://www.example.com/general"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
      <number1>1</number1>
      <number3>2</number3><number3>1</number3>
      <number4>1</number4>
      <number5 xsi:nil="true"/>
      <milk1>WHOLE</milk1>
      <milk3>WHOLE</milk3><milk3>SKIM</milk3>
      <milk5 xsi:nil="true"/>
    </foo>
    val obj = fromXML[SingularSimpleTypeTest](subject)
    obj match {
      case SingularSimpleTypeTest(1, None, Seq(2, 1), Some(1), None,
        WHOLE, None, Seq(WHOLE, SKIM), None, None,
        None, None) =>
      case _ => error("match failed: " + obj.toString)
    }
    val document = toXML[SingularSimpleTypeTest](obj, None, Some("foo"), subject.scope)
    println(document)    
  }
  
  def testList {
    val subject = <foo xmlns="http://www.example.com/general"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
      <numbers1>1 2 3</numbers1>
      <numbers3></numbers3><numbers3>1</numbers3>
      <numbers4>1</numbers4>
      <numbers5 xsi:nil="true"/>
      <milk1>WHOLE</milk1>
      <milk3></milk3><milk3>SKIM</milk3>
      <milk5 xsi:nil="true"/>
    </foo>
    val obj = fromXML[ListTest](subject)
    obj match {
      case ListTest(Seq(1, 2, 3), None, Seq(Seq(), Seq(1)), Some(Seq(1)), None,
        Seq(WHOLE), None, Seq(Seq(), Seq(SKIM)), None, None,
        None, None) =>
      case _ => error("match failed: " + obj.toString)
    }
    val document = toXML[ListTest](obj, None, Some("foo"), subject.scope)
    println(document)
  }
}