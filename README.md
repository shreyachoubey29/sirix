#Sirix - Secure Treebased Storage

Sirix stores data securely by applying different layers on the stored data. Flexible handling of flat as well as tree-based data is supported by a native encoding of the tree-structures ongoing with suitable paging supporting integrity and confidentiality to provide throughout security.

Secure in this context includes integrity-checks, confidentiality with tree-aware key handling, versioning to provide accountability.
Furthermore, different backends are provided while a binding to different cloud-infrastructures is in progress.

The architecture supports the well known ACID-properties and MVCC through Snapshot Isolation which in turn supports N-reading transactions in parallel to currently 1-write transaction. Supporting N-write transactions is planned as well as the current work on indexes to support a binding to Brackit which in turn supports XQuery/XQuery Update Facility. The COW-approach used for providing MVCC through Snapshot Isolation is especially well suited for flash-disks.

Any questions or even consider to contribute or use Sirix? Just contact lichtenberger.johannes AT gmail.com.

Note that it is based on Treetank (http://treetank.org / http://github.com/disy/treetank).

[![Build Status](https://secure.travis-ci.org/JohannesLichtenberger/sirix.png)](http://travis-ci.org/JohannesLichtenberger/sirix)

##Content

* README:					this readme file
* LICENSE:	 				license file
* bundles					all available bundles
* pom.xml:					Simple pom (yes we do use Maven)

##Further information

As Sirix is based on Treetank current documentation thus is currently only available from this location.

The documentation so far is accessible under http://treetank.org (pointing to http://disy.github.com/treetank/).

The framework was presented at various conferences and acted as base for multiple publications and reports:

* A legal and technical perspective on secure cloud Storage; DFN Forum'12: [PDF](http://nbn-resolving.de/urn:nbn:de:bsz:352-192389)
* A Secure Cloud Gateway based upon XML and Web Services; ECOWS'11, PhD Symposium: [PDF](http://kops.ub.uni-konstanz.de/handle/urn:nbn:de:bsz:352-154112)
* Treetank, Designing a Versioned XML Storage; XMLPrague'11: [PDF](http://kops.ub.uni-konstanz.de/handle/urn:nbn:de:bsz:352-opus-126912)
* Rolling Boles, Optimal XML Structure Integrity for Updating Operations; WWW'11, Poster: [PDF](http://kops.ub.uni-konstanz.de/handle/urn:nbn:de:bsz:352-126226)
* Hecate, Managing Authorization with RESTful XML; WS-REST'11: [PDF](http://kops.ub.uni-konstanz.de/handle/urn:nbn:de:bsz:352-126237)
* Integrity Assurance for RESTful XML; WISM'10: [PDF](http://kops.ub.uni-konstanz.de/handle/urn:nbn:de:bsz:352-opus-123507)
* JAX-RX - Unified REST Access to XML Resources; TechReport'10: [PDF](http://kops.ub.uni-konstanz.de/handle/urn:nbn:de:bsz:352-opus-120511)
* Distributing XML with focus on parallel evaluation; DBISP2P'08: [PDF](http://kops.ub.uni-konstanz.de/handle/urn:nbn:de:bsz:352-opus-84487)

##License

This work is released in the public domain under the BSD 3-clause license


##Involved People

Sirix is maintained by:

* Johannes Lichtenberger (Sirix Core & Project Lead based on Treetank Core)
