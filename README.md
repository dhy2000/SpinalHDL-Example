# README

SpinalHDL 工程模板， 示例模块来自于 [SpinalTemplateSbt](https://github.com/SpinalHDL/SpinalTemplateSbt) 。

## 目录结构

- `build.sbt` SBT 工程定义（请勿修改！）
- `src/main/scala` 工程代码 (模块) 目录
- `src/test/scala` 测试代码 (仿真) 目录

## 生成 Verilog

```bash
sbt "runMain TopModule"
```

Verilog 代码生成在 `output_dir` （由 `SpinalConfig` 的 `targetDirectory` 参数指定）。

## 运行仿真

```bash
sbt "test:runMain TopModuleSim"
```

仿真文件生成在 `simWorkspace` 内以 Verilog 顶层模块名称（不一定是 Scala 类名）命名的子目录。

## Verdi 查看波形

```bash
cd simWorkspace/top_module
verdi -ssf top_module.fsdb
```
