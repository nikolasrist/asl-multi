import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import kotlinx.cli.required
import java.io.File

fun main(args: Array<String>) {
    val parser = ArgParser("Multi ASL")
    val input by parser.option(ArgType.String, shortName = "i", description = "Input folder").required()
    val fslAnatOutput by parser.option(ArgType.String, shortName = "f", description = "FSL_Anat output folder")
        .required()
    val calibrationImagePath by parser.option(ArgType.String, shortName = "c", description = "Calibration image path")
        .required()
    val rptsValue by parser.option(ArgType.String, shortName = "r", description = "RPTS value").default("1")
    val debug by parser.option(ArgType.Boolean, shortName = "d", description = "Activate debug logging").default(false)

    parser.parse(args)

    val inputFiles = File(input).walk().filter { it.name.endsWith(".nii") && !it.name.contains("gleichsinnig") && !it.name.contains("gegensinnig") }
    inputFiles.forEach {
        val inputFile = it.name
        val outputPath = it.path.split("/")
        val outputPathString = outputPath.drop(0).dropLast(1).joinToString("/")
        println("OutPutPath: $outputPathString")
        println("Path: $it.path")
        val caller = initCaller(inputFile, outputPathString, rptsValue, fslAnatOutput, calibrationImagePath)
    if (debug) {
        println("DEBUG MODE:")
        println("CALL String: \n ${caller.toCallString()}")
    } else {
        println("START CALCULATIONS:")
        println("CALL String: \n ${caller.toCallString()}")
        Runtime.getRuntime().exec(caller.toCallString())
    }
    }

}

fun initCaller(
    inputFile: String,
    output: String,
    rptsValue: String,
    fslAnatOutput: String,
    calibrationImagePath: String
): AslCaller {
    return AslCaller.Builder()
        .input(inputFile)
        .output(output)
        .iaf(IAFValues.TC.value)
        .ibf(IBFValues.RPT.value)
        .casl()
        .bolus(listOf("1.8"))
        .rpts(listOf(rptsValue, rptsValue, rptsValue, rptsValue))
        .tis(listOf("3.7", "3.15", "2.6", "2.05"))
        .bat("1.3")
        .t1("1.3")
        .t1b("1.65")
        .fslAnat(fslAnatOutput)
        .calibration(calibrationImagePath)
        .calibrationMethod("voxel")
        .calibrationTr("6")
        .alpha("0.85")
        .spatial()
        .fixbolus()
        .artoff()
        .build()
}