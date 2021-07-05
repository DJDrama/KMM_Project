package com.dj.kmm.domain.util

import com.dj.kmm.domain.model.GenericMessageInfo

class GenericMessageInfoQueueUtil {
    /**
     * Does this particular GenericMessageInfo already exist in the queue? We don't want duplicates
     */
    fun doesMessageAlreadyExistInQueue(queue: Queue<GenericMessageInfo>, messageInfo: GenericMessageInfo): Boolean{
        for(item in queue.items){
            if (item.id == messageInfo.id){
                return true
            }
        }
        return false
    }
}