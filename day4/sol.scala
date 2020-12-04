import scala.io.Source
import scala.collection.mutable.StringBuilder
import scala.util.matching.Regex

object Main extends App {

    def sol1(lines: Iterator[String]): Int = {
        val requiredFields = List("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
        val passportInfo = new StringBuilder("")
        var validPassportCounter = 0
        for (line <- lines) {
            if (line == "") {
                val passportFields = passportInfo.result().strip().split(" ")
                passportInfo.clear()
                val passportFieldTags = for (field <- passportFields) yield field.split(":")(0)
                if (requiredFields.forall(passportFieldTags.contains)) {
                    validPassportCounter += 1
                }
            } else {
                passportInfo ++= (" " + line.trim())
            }
        }
        return validPassportCounter
    }

    object RegexUtils {
        implicit class RichRegex(val underlying: Regex) extends AnyVal {
            def matches(s: String) = underlying.pattern.matcher(s).matches
        }
    }

    def isValidByr(value: String): Boolean = {
        value.length() == 4 && value.toInt >= 1920 && value.toInt <= 2002
    }

    def isValidIyr(value: String): Boolean = {
        value.length() == 4 && value.toInt >= 2010 && value.toInt <= 2020
    }

    def isValidEyr(value: String): Boolean = {
        value.length() == 4 && value.toInt >= 2020 && value.toInt <= 2030
    }

    def isValidHgt(value: String): Boolean = {
        value.takeRight(2) match {
            case "cm" => value.dropRight(2).toInt >= 150 && value.dropRight(2).toInt <= 193
            case "in" => value.dropRight(2).toInt >= 59 && value.dropRight(2).toInt <= 76
            case _ => false 
        }
    }

    def isValidHcl(value: String): Boolean = {
        val pattern = raw"#([0-9a-f]{6})".r
        pattern matches value
    }

    def isValidEcl(value: String): Boolean = {
        Set("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(value)
    }

    def isValidPid(value: String): Boolean = {
        val pattern = raw"\d{9}".r
        pattern matches value
    }

    def sol2(lines: Iterator[String]): Int = {
        val requiredWithValidators = Map("byr" -> isValidByr _, "iyr" -> isValidIyr _, "eyr" -> isValidEyr _, "hgt" -> isValidHgt _, "hcl" -> isValidHcl _, "ecl" -> isValidEcl _, "pid" -> isValidPid _)
        val passportInfo = new StringBuilder("")
        var validPassportCounter = 0
        for (line <- lines) {
            if (line == "") {
                val passportFields = passportInfo.result().strip().split(" ")
                passportInfo.clear()
                val passportFieldsWithValues = (for (field <- passportFields) yield (field.split(":")(0), field.split(":")(1))).toMap
                if (requiredWithValidators.forall{ case (k, v) => passportFieldsWithValues.contains(k) && v(passportFieldsWithValues(k)) }) {
                    validPassportCounter += 1
                }
            } else {
                passportInfo ++= (" " + line.trim())
            }
        }
        return validPassportCounter
    }

    val filename = "input.txt"
    val data = Source.fromFile(filename).getLines()
    println(sol2(data))

}