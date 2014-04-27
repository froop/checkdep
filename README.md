CheckDep (Check Dependency)
====================

Dependency Checker for Java Packages.

Javaのパッケージ間の依存性を検証し、可視性制約を擬似的に実現するツール。JUnit等の自動テストから使用されることを想定。


存在理由
--------------------

Javaは、クラスに対しては可視性(public/protected/package)を指定して使用を制限できるが、パッケージにはそのような機能はない。
そのため、細かくパッケージを分けて階層化したほうが自然な場合であっても、可視性を制約したいがために同一パッケージに詰め込むことがあり、
コードの検索性が低下したり、理解の妨げになったり（マジカルナンバー7）することがあった。

また、下記のようなパッケージ依存関係の制約がコード内では表現できず、コンパイルで検出できないため、
意図しない依存関係ができてしまうことがあった。
* OK: A -> B -> C
* NG: A -> C
* NG: B -> A

パッケージ依存性に関するメトリクスツールである JDepend (http://clarkware.com/software/JDepend.html) には、
その検出を意図した機能が一応あるが (JDepend#dependencyMatch())、
プロジェクト全体についてのbooleanしか返さず、具体的にどの依存性に違反があるのかが取れないため使いづらい。


実現方式
--------------------

Javaコードの解析はJDependを利用し、依存性の検証のみを独自に開発する。


前提環境
--------------------

Java 8
