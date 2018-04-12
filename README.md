# oo-crypto


## Overview

oo-crypto is a package of pure-Java object oriented utilities for dealing with encryption and encoding of data.

## Usage

```java
// SHA-256 of some data.
Sha256Of hashed = new Sha256Of(new TextData("The quick brown fox jumps over the lazy dog"));

// SHA-256 is also a data source, and can be hashed again.
Sha256Of doubleHashed = new Sha256Of(hashed);

// Hashing results in binary data. There is a series of text encoders do deal with them.
StringEncoded base16 = new Base16Encoded(doubleHashed);
StringEncoded base32 = new Base32Encoded(doubleHashed);
StringEncoded base64 = new Base64Encoded(doubleHashed);

// Up to this point, no data is actually read from any data source, and no hashing was done. This will only happen when I use it.
try {
  System.out.println(base16.asString());
  System.out.println(base32.asString());
  System.out.println(base64.asString());
} catch(IOException ex) {
  // Now the data was actually read. Things can happen
}
```

## License

Code is under the MIT License