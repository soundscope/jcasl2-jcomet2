[![Build Status](https://travis-ci.org/kusumotolab/jcasl2-jcomet2.svg?branch=master)](https://travis-ci.org/kusumotolab/jcasl2-jcomet2)

# JCasl2 & JComet2

[CASLIIアセンブラ & シミュレータ PyCASL2 & PyCOMET2](http://www.image.med.osaka-u.ac.jp/member/nakamoto/pycasl2/)
のJava実装です．  
JavaさえあればCASL&COMETを実行することができます．  
また実行パフォーマンスも大幅に向上しています．  

また以下のバグも修正しています．

- 文字定数に含まれるアポストロフィを正しく処理できない
- ラベルが9文字以上または英小文字またはアンダーバーが含まれていてもコンパイルする
- 対話モードでデバッグポイントの削除ができない

## 概要

**JCasl2 & JComet2**は CASLII のアセンブラおよびシミュレータです．  
大阪大学基礎工学部情報科学科3年生の「情報科学演習D」で用いるために開発されました．  
演習用に一部の仕様が拡張されています．  

### 拡張された仕様

- 汎用レジスタGR8が増設されています．GR8はスタックポインタ(SP)を兼ねており，GR8の値を変更することはスタックポインタの値を変更することと等価です．また，RPUSH, RPOP命令ではGR8も操作対象となっています．
- DC, DS命令はRET命令とEND命令の間にのみ，置くことができます．(JCasl2 1.1以降) START命令とRET命令の間に置かれた場合はエラーになります．

## 動作環境

実行にはJava Development Kit 8以降が必要です．

## ダウンロード

- https://github.com/kusumotolab/jcasl2-jcomet2/releases

## マニュアル

### JCasl2

#### 使い方

```
$ java -jar jcasl2.jar [option] input.cas [output.com]
```

#### 引数

- `input.cas` : アセンブルするファイルを指定します．
- `output.com` : 出力するオブジェクトファイルを指定します．output.comを指定しなかった場合，input.casで指定されたファイルの拡張子を comに変えた名前が出力ファイル名になります．（例：example.casを指定した場合，example.comが出力される）

#### オプション

- `-a` : 各コードに対応する機械語コード，およびラベルに対応するメモリ番地といった詳細情報を標準出力に出力します．
- `-v`, `--version` : バージョン情報を表示します．
- `-h`, `--help` : ヘルプを表示します．

#### 使用例

example.casをアセンブルし，program.comを生成します．

```
$ java -jar jcasl2.jar example.cas program.com
```

example.casをアセンブルし，program.comを生成します．また詳細情報出力します．

```
$ java -jar jcasl2.jar -a example.cas program.com
Addr    Op              Line    Source code
0000    1250            2               LAD     GR5, 1
0001    0001
0002    8100            3               RET

Defined labels
example.cas:1      0000    MAIN
```

### JComet2

#### 使い方

```
java -jar jcomet2.jar [options] input.com
```

デフォルトでは対話モードで起動します．
対話モードではコマンドを入力することで，レジスタやメモリの値の確認， ブレークポイントの設定，一命令ずつの実行などが行えます．

`-r`オプションを指定すると，対話モードに入ることなくプログラムが実行され，終了するとプロンプトに戻ります．

#### 引数

- `input.com` : 実行するオブジェクトファイルを指定します．

#### オプション

- `-c` : 終了時に実行された命令数を標準出力に出力します．
- `-d` : 終了時にレジスタとメモリの値，実行された命令数をlast_state.txtというテキストに出力します．既にlast_state.txtが存在する場合は上書きされます．
- `-r` : 対話モードに入ることなく実行します．
- `-w target` : target の値を標準出力に表示しながら実行します．対話モードには入りません．PRが指す命令を実行する前の状態が表示されます．左端にはステップ数が表示され，その右側に target の値が表示されます． target は以下の対象をカンマ(,)で区切って複数指定できます．間にスペースを入れてはいけません．（例: `-w PR,GR0,#001b`）
  - `PR`, `OF`, `SF`, `ZF` : 各レジスタの値．
  - `GR[0-8]` : GR0からGR8までのレジスタの値．
  - `10進数または16進数（#xxxx）` : その番地のメモリの値．
- `-D` : `-w`が指定されたときに，GR[0-8]および指定した番地のメモリの値を10進数で表示します．
- `-v`, `--version` : バージョン情報を表示します．
- `-h`, `--help` : ヘルプを表示します．

#### 対話モード

対話モードでは以下のコマンドを使用できます． アドレスや値は10進数または16進数（#xxxx）で指定できます．

- `s` : 命令を一つだけ実行します．実行後には各レジスタの値が表示されます．
- `r` : プログラムを実行します．プログラムが終了した場合，シミュレータを終了します．プログラムが停止しない場合は，Ctrl-Cで強制終了させてください．
- `b addr` : ブレイクポイントを addr 番地に設定します．
- `d number` : 指定した番号のブレイクポイントを削除します．
- `i` : 設定されているブレイクポイントを表示します．
- `du [ addr ]` : addr 番地から128語分のメモリの内容を表示します．
- `st` : スタックの内容を128語分表示します．
- `di [ addr ]` : addr 番地から32語分のメモリの内容をディスアセンブルします．番地を指定しない場合は#0000番地からディスアセンブルされます．
- `j addr` : プログラムレジスタの値を addr にセットします．
- `m addr val` : addr 番地のメモリの値を val にセットします．
- `p` : 各レジスタの値を表示します． レジスタの値は16進数と10進数の両方で表示されます．
- `q` : シミュレータを終了します．
- `h` : コマンドのヘルプを表示します．

#### 使用例
example.comを対話モードで実行します．:

```
$ java -jar jcomet2.jar example.com
```

example.comを非対話モードで実行し，終了時の状態を保存します．:

```
$ java -jar jcomet2.jar -d -r example.com
```

example.comを非対話モードで実行します．実行中はPR, GR0, メモリの#001b番地の各値が表示されます．:

```
$ java -jar jcomet2.jar -w PR,GR0,#001b example.com
0000: PR=#0000, GR0=#0000, #001b=#000f
0001: PR=#0002, GR0=#0001, #001b=#000f
0002: PR=#0004, GR0=#0001, #001b=#000f
...
```

## ライセンス

**JCasl2 & JComet2**はGPL2に基づくフリーソフトウェアとして公開しています．
