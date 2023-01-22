class AslCaller private constructor(
    val input: String?,
    val output: String?,
    val mask: String?,
    val spatial: String?,
    val whitePaperMode: String?,
    val motionCorrection: String?,
    val infert1: String?,
    val iaf: String?, // <diff, tc, ct>
    val ibf: String?, // <rpt,tis>
    val casl: String?,
    val tis: String?, // <csv>
    val bolus: String?, // <value> or <csv>
    val slicedt: String?,
    val bat: String?,
    val t1: String?,
    val t1b: String?,
    val sliceband: String?,
    val rpts: String?, //csv
    val fslAnatOutput: String?, // output directory
    val calibration: String?, // -c <image_path>
    val calibrationTr: String?, // --tr=<value>
    val calibrationGain: String?, // --tr=<value>
    val calibrationTissref: String?,
    val calibrationT1csf: String?,
    val calibrationT2csf: String?,
    val calibrationT2bl: String?,
    val calibrationTe: String?,
    val calibrationAlpha: String?, // --tr=<value>
    val calibrationMethod: String?, // --cmethod=<single, voxel>
    val calibrationM0: String?, // --M0=<value>
    val alpha: String?,
    val fixbolus: String?,
    val artoff: String?,
    var echospacing: String?,
    var region_analysis: String?,
    var region_analysis_atlas: String?,
    var region_analysis_atlas_labels: String?,
    var pvcorr: String?,
    var pedir: String?,
    var cblip: String?
) {
    val INITIAL_CALL_STRING = "oxford_asl "
    fun toCallString(): String {
        return INITIAL_CALL_STRING.appendValue(this.input)
            .appendValue(this.iaf)
            .appendValue(this.ibf)
            .appendValue(this.casl)
            .appendValue(this.bolus)
            .appendValue(this.rpts)
            .appendValue(this.tis)
            .appendValue(this.fslAnatOutput)
            .appendValue(this.region_analysis)
            .appendValue(this.region_analysis_atlas)
            .appendValue(this.region_analysis_atlas_labels)
            .appendValue(this.calibration)
            .appendValue(this.calibrationMethod)
            .appendValue(this.calibrationTr)
            .appendValue(this.calibrationGain)
            .appendValue(this.output)
            .appendValue(this.bat)
            .appendValue(this.t1)
            .appendValue(this.t1b)
            .appendValue(this.alpha)
            .appendValue(this.fixbolus)
            .appendValue(this.artoff)
            .appendValue(this.spatial)
            .appendValue(this.whitePaperMode)
            .appendValue(this.motionCorrection)
            .appendValue(this.infert1)
            .appendValue(this.calibrationTissref)
            .appendValue(this.slicedt)
            .appendValue(this.sliceband)
            .appendValue(this.calibrationAlpha)
            .appendValue(this.calibrationT1csf)
            .appendValue(this.calibrationT2csf)
            .appendValue(this.calibrationT2bl)
            .appendValue(this.calibrationTe)
            .appendValue(this.calibrationM0)
            .appendValue(this.pvcorr)
            .appendValue(this.pedir)
            .appendValue(this.cblip)
            .appendValue(this.echospacing)
    }

    private fun String.appendValue(value: String?): String {
        return this + (value ?: "")
    }

    data class Builder(
        var input: String? = null,
        var output: String? = null,
        var mask: String? = null,
        var spatial: String? = null,
        var artoff: String? = null,
        var fixbolus: String? = null,
        var alpha: String? = null,
        var whitePaperMode: String? = null,
        var motionCorrection: String? = null,
        var iaf: String? = null, // <diff, tc, ct>
        var ibf: String? = null, // <rpt,tis>
        var casl: String? = null,
        var tis: String? = null, // <csv>
        var bolus: String? = null, // <value> or <csv>
        var slicedt: String? = null,
        var bat: String? = null,
        var t1: String? = null,
        var t1b: String? = null,
        var sliceband: String? = null,
        var rpts: String? = null, //csv
        var fslAnatOutput: String? = null, // output directory
        var calibration: String? = null, // -c <image_path>
        var calibrationTr: String? = null, // --tr=<value>
        var calibrationGain: String? = null, // --tr=<value>
        var calibrationTissref: String? = null,
        var calibrationT1csf: String? = null,
        var calibrationT2csf: String? = null,
        var calibrationT2bl: String? = null,
        var calibrationTe: String? = null,
        var calibrationAlpha: String? = null, // --tr=<value>
        var calibrationMethod: String? = null, // --cmethod=<single, voxel>
        var calibrationM0: String? = null, // --M0=<value>
        var echospacing: String? = null,
        var region_analysis: String? = null,
        var region_analysis_atlas: String? = null,
        var region_analysis_atlas_labels: String? = null,
        var infert1: String? = null,
        var pvcorr: String? = null,
        var pedir: String? = null,
        var cblip: String? = null
    ) {

        fun input(inputPath: String) = apply { this.input = " -i ${inputPath}" }
        fun output(outputPath: String) = apply { this.output = " -o ${outputPath}" }
        fun mask(mask: String) = apply { this.mask = " -m ${mask}" }
        fun spatial() = apply { this.spatial = " --spatial" }
        fun whitePaperMode() = apply { this.whitePaperMode = " --wp" }
        fun motionCorrection() = apply { this.motionCorrection = " --mc" }
        fun iaf(value: String) = apply { this.iaf = " --iaf ${value}" }
        fun ibf(value: String) = apply { this.ibf = " --ibf ${value}" }
        fun casl() = apply { this.casl = " --casl" }
        fun tis(value: List<String>) = apply { this.tis = " --tis=${transformToCSV(value)}" }
        fun bolus(value: List<String>) = apply { this.bolus = " --bolus ${transformToCSV(value)}" }
        fun slicedt(value: String) = apply { this.slicedt = " --slicedt ${value}" }
        fun bat(value: String) = apply { this.bat = " --bat ${value}" }
        fun t1(value: String) = apply { this.t1 = " --t1 ${value}" }
        fun t1b(value: String) = apply { this.t1b = " --t1b ${value}" }
        fun sliceband(value: String) = apply { this.sliceband = " --sliceband ${value}" }
        fun rpts(value: List<String>) = apply { this.rpts = " --rpts ${transformToCSV(value)}" }
        fun fslAnat(outputPath: String) = apply { this.fslAnatOutput = " --fslanat=${outputPath}" }
        fun calibration(imagePath: String) = apply { this.calibration = " -c ${imagePath}" }
        fun calibrationTr(value: String) = apply { this.calibrationTr = " --tr ${value}" }
        fun calibrationGain(value: String) = apply { this.calibrationGain = " --cgain ${value}" }
        fun calibrationTissref(value: String) = apply { this.calibrationTissref = " --tissref ${value}" }
        fun calibrationT1csf(value: String) = apply { this.calibrationT1csf = " --t1csf ${value}" }
        fun calibrationT2csf(value: String) = apply { this.calibrationT2csf = " --t2csf ${value}" }
        fun calibrationT2bl(value: String) = apply { this.calibrationT2bl = " --t2bl ${value}" }
        fun calibrationTe(value: String) = apply { this.calibrationTe = " --te ${value}" }
        fun tissref(value: String) = apply { this.calibrationTissref = " --tissref=${value}" }
        fun calibrationAlpha(value: String) = apply { this.calibrationAlpha = " --alpha ${value}" }
        fun calibrationMethod(value: String) = apply { this.calibrationMethod = " --cmethod ${value}" }
        fun calibrationM0(value: String) = apply { this.calibrationM0 = " --M0 ${value}" }
        fun alpha(value: String) = apply { this.alpha = " --alpha ${value}" }
        fun fixbolus() = apply { this.fixbolus = " --fixbolus" }
        fun artoff() = apply { this.artoff = " --artoff" }
        fun echospacing(value: String) = apply { this.echospacing = " --echospacing=${value}" }
        fun region_analysis() = apply { this.region_analysis = " --region-analysis" }
        fun region_analysis_atlas(value: String?) = apply {
            if (value != null) this.region_analysis_atlas =
                " --region-analysis-atlas ${value}" else this.region_analysis_atlas = ""
        }

        fun region_analysis_atlas_labels(value: String?) = apply {
            if (value != null) this.region_analysis_atlas_labels =
                " --region-analysis-atlas_labels ${value}" else this.region_analysis_atlas_labels = ""
        }

        fun infert1() = apply { this.infert1 = " --infert1" }
        fun pvcorr() = apply { this.pvcorr = " --pvcorr" }
        fun pedir() = apply { this.pedir = " --pedir y" }
        fun cblip(value: String) = apply { this.cblip = " --cblip ${value}" }

        fun build() = AslCaller(
            input,
            output,
            mask,
            spatial,
            whitePaperMode,
            motionCorrection,
            infert1,
            iaf,
            ibf,
            casl,
            tis,
            bolus,
            slicedt,
            bat,
            t1,
            t1b,
            sliceband,
            rpts,
            fslAnatOutput,
            calibration,
            calibrationTr,
            calibrationGain,
            calibrationTissref,
            calibrationT1csf,
            calibrationT2csf,
            calibrationT2bl,
            calibrationTe,
            calibrationAlpha,
            calibrationMethod,
            calibrationM0,
            alpha,
            fixbolus,
            artoff,
            echospacing,
            region_analysis,
            region_analysis_atlas,
            region_analysis_atlas_labels,
            pvcorr,
            pedir,
            cblip
        )
    }
}

fun transformToCSV(value: List<String>): String {
    return value.joinToString(",")
}

enum class IAFValues(val value: String) {
    DIFF("diff"),
    TC("tc"),
    CT("ct")
}

enum class IBFValues(val value: String) {
    RPT("rpt"),
    TIS("tis")
}

enum class CMETHODValues(val value: String) {
    SINGLE("single"),
    VOXEL("voxel")
}