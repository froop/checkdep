CheckDep (Check Dependency)
====================

A Java package dependency checker.

Javaのパッケージ間の依存性を検証し、可視性制約を擬似的に実現するツール。JUnit等の自動テストから使用されることを想定。


使用例
--------------------

```java
@Test
public void test() {
  Violations res = CheckDep.check(
      SourceDirectories.of(
          "target/classes"),
      ExcludePackages.of(
          "java.lang",
          "java.util",
          "org.apache.commons.lang3",
          "com.google.common.collect",
          "checkdep.util"),
      Constraints.builder()
          .add("checkdep", "checkdep.check.*")
          .add("checkdep", "checkdep.parse.*")
          .add("checkdep", "checkdep.value.*")
          .add("checkdep.*", "checkdep.common.*")
          .add("checkdep.check.*", "checkdep.value.*")
          .add("checkdep.common.*", "checkdep.value.*")
          .add("checkdep.common.*", "jdepend.framework")
          .add("checkdep.parse.*", "checkdep.value.*")
          .add("checkdep.parse.*", "java.io")
          .add("checkdep.parse.*", "jdepend.framework")
          .add("checkdep.value.*", "checkdep.value.*")
          .build());

  assertTrue(res.toString(), res.isEmpty());
}
```


前提環境
--------------------

* Java SE 8
* JDepend 2.9 (http://clarkware.com/software/JDepend.html)
  -> 解析対象の .class で Java 8 の Stream を使っているとエラーになるので、https://github.com/froop/jdepend で対応
* Project Lombok 1.12.6 (http://projectlombok.org/)
* Google Guava 17 (http://code.google.com/p/guava-libraries/)
* Apache Commons Lang 3.3 (http://commons.apache.org/proper/commons-lang/)


存在理由
--------------------

Javaは、クラスに対しては可視性(public/protected/package)を指定して使用を制限できるが、パッケージにはそのような機能はない。
そのため、細かくパッケージを分けて階層化したほうが自然な場合であっても、可視性を制約したいがために同一パッケージに詰め込むことがあり、
コードの検索性が低下したり、理解の妨げになったり（マジカルナンバー7）することがあった。

また、下記のようなパッケージ依存関係の制約がコード内では表現できず、コンパイルで検出できないため、
意図しない依存関係ができてしまうことがあった。
* OK: A -> B -> C
* NG: B -> A (逆方向への依存はダメ)
* NG: A -> C (層を飛び越した依存はダメ)

パッケージ依存性に関するメトリクスツールである JDepend (http://clarkware.com/software/JDepend.html) には、
その検出を意図した機能が一応あるが (JDepend#dependencyMatch())、
プロジェクト全体についてのbooleanしか返さず、具体的にどの依存性に違反があるのかが取れないため使いづらい。


個人的開発理由
--------------------

* Java 8 を使ってみる。関数型に興味。Stream API とか便利そう。
* Apache Maven を使ってみる。ビルドツールはAntしか使ってなかったので。
* Lombok を使ってみる。Getter、Constructor, equals, hashCode, toString。
* Google Guava を使ってみる。ImmutableCollection目当て。


実現方式
--------------------

Javaコードの解析はJDependを利用し、依存性の検証のみを独自に開発する。
