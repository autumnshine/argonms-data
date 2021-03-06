/*
 *  KvJ Compiler for XML WZ data files
 *  Copyright (C) 2010-2013  GoldenKevin
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package kvjcompiler.string.structure;

import kvjcompiler.IStructure;
import kvjcompiler.LittleEndianWriter;
import kvjcompiler.Size;

/**
 *
 * @author GoldenKevin
 */
public class CashStrings implements IStructure {
	private final int entryid;

	private String name;
	private String msg;

	public CashStrings(int id) {
		this.entryid = id;
	}

	@Override
	public void setProperty(String key, String value) {
		if (key.equals("name")) {
			this.name = value;
		} else if (key.equals("msg")) {
			this.msg = value;
		}/* else {
			System.out.println("WARNING: Unhandled property " + key + " in entry " + entryid + ".");
		}*/
	}

	@Override
	public int size() {
		int size = Size.INT;
		if (name != null) size += name.length();
		size += Size.BYTE;
		if (msg != null) size += msg.length();
		size += Size.BYTE;
		return size;
	}

	@Override
	public void writeBytes(LittleEndianWriter lew) {
		lew.writeInt(entryid);
		lew.writeNullTerminatedString(name);
		lew.writeNullTerminatedString(msg);
	}
}
