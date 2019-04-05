#ifndef OPENSLRECORD_RECORDBUFFER_H
#define OPENSLRECORD_RECORDBUFFER_H

class RecordBuffer {
public:
    short **buffer;
    int index = -1;
public:
    RecordBuffer(int buffersize);
    ~RecordBuffer();
    /**
     * 得到一个新录制的buffer
     * @return
     */
    short* getRecordBuffer();
    /**
     * 得到当前录制的buffer
     * @return
     */
    short* getNowBuffer();
};
#endif